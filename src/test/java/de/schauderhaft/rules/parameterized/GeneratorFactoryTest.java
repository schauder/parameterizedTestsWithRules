package de.schauderhaft.rules.parameterized;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runners.model.Statement;

public class GeneratorFactoryTest {

    public class CollectingStatement<T> extends Statement {

        public Set<T> values = new HashSet<>();
        private final Generator<T> g;

        public CollectingStatement(Generator<T> g) {
            this.g = g;
        }

        @Override
        public void evaluate() throws Throwable {
            values.add(g.value());
        }
    }

    private class ExceptionThrowingStatement extends Statement {

        @Override
        public void evaluate() throws Throwable {
            throw new RuntimeException();
        }

    }

    private class CountingStatement extends Statement {

        private int count = 0;

        @Override
        public void evaluate() throws Throwable {
            count++;
        }
    }

    @Test
    public void generatorWithNElementsRunsTheTestNtimes() throws Throwable {
        Generator<Integer> g = GeneratorFactory.list(1, 2, 3, 4);
        CountingStatement probe = new CountingStatement();

        g.apply(probe, null).evaluate();

        assertThat(probe.count, equalTo(4));
    }

    @Test
    public void statementSeesAllValues() throws Throwable {
        Generator<Integer> g = GeneratorFactory.list(1, 2, 3, 4);
        CollectingStatement<Integer> probe = new CollectingStatement<>(g);

        g.apply(probe, null).evaluate();

        assertThat(probe.values,
                CoreMatchers.equalTo((Set) new HashSet<>(asList(1, 2, 3, 4))));
    }

    @Test(expected = Throwable.class)
    public void failuresGetPropagated() throws Throwable {
        Generator<Integer> g = GeneratorFactory.list(1);
        Statement probe = new ExceptionThrowingStatement();

        g.apply(probe, null).evaluate();
    }

    @Test
    public void failureMessageContainsGeneratorValueWhenAnExceptionIsThrown()
            throws Throwable {
        Generator<String> g = GeneratorFactory.list("xXx");
        Statement probe = new ExceptionThrowingStatement();

        try {
            g.apply(probe, null).evaluate();
        } catch (AssertionError e) {
            assertTrue(e.getMessage(), e.getMessage().contains("xXx"));
        }
    }

    @Test
    public void tupleRunTheTestsForTheCrossProductOfAllValues()
            throws Throwable {
        Generator<Tuple2<Integer, String>> g = GeneratorFactory.tuples(
                asList(1, 3, 4), asList("alpha", "beta"));
        CollectingStatement<Tuple2<Integer, String>> probe = new CollectingStatement<>(
                g);

        g.apply(probe, null).evaluate();

        assertEquals(
                new HashSet<Tuple2<Integer, String>>(asList(
                        new Tuple2<Integer, String>(1, "alpha"),
                        new Tuple2<Integer, String>(3, "alpha"),
                        new Tuple2<Integer, String>(4, "alpha"),
                        new Tuple2<Integer, String>(1, "beta"),
                        new Tuple2<Integer, String>(3, "beta"),
                        new Tuple2<Integer, String>(4, "beta"))), probe.values);
    }
}
