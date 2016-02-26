package de.schauderhaft.rules.parameterized;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;

public class TupleRuleTest {
    @Rule
    public Generator<Tuple2<Integer, Integer>> generator = GeneratorFactory
            .tuples(asList(84, 42, 120), asList(1, 2, 3));

    @Test
    public void allGivenNumbersAreX() {
        assertEquals(0, generator.value()._1 % generator.value()._2);
    }
}
