package de.schauderhaft.rules.parameterized;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertTrue;

import org.junit.Rule;
import org.junit.Test;

public class ExampleTest {

    @Rule
    public Generator<Tuple2<Integer, String>> tuples = GeneratorFactory.tuples(
            asList(1, 2, 3, 4), asList("a", "b", "c"));

    @Test
    public void testSomething() throws Exception {
        assertTrue(tuples.value()._1 % 2 == 0 || tuples.value()._2.length() > 2);
    }

    @Test
    public void throwingUp() {
        throw new RuntimeException();
    }
}
