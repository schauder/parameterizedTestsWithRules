package de.schauderhaft.rules.parameterized;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Rule;
import org.junit.Test;

import de.schauderhaft.rules.parameterized.Generator;
import de.schauderhaft.rules.parameterized.GeneratorFactory;

public class ListRuleTest {
    @Rule
    public Generator<Integer> generator = GeneratorFactory.list(23, 42, 5);

    @Test
    public void allGivenNumbersAreX() {
        assertTrue(Arrays.asList(5, 42, 23).contains(generator.value()));
    }
}
