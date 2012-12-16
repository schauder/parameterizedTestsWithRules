package de.schauderhaft.rules.parameterized;

import org.junit.rules.TestRule;

public interface Generator<T> extends TestRule {
    public T value();
}
