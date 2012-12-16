package de.schauderhaft.rules.parameterized;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Rule;
import org.junit.Test;

public class SimpleExampleTest {

    @Rule
    public Generator<String> params = new ListGenerator(Arrays.asList("alpha",
            "beta", "gamma"));

    @Test
    public void testSomething() throws Exception {
        assertTrue(params.value().length() >= 4);
    }

    @Test
    public void throwingUp() {
        throw new RuntimeException();
    }
}
