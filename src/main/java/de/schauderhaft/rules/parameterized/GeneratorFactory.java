package de.schauderhaft.rules.parameterized;

import static java.util.Arrays.asList;

public final class GeneratorFactory {
    private GeneratorFactory() {
    }

    public static <T> Generator<T> list(T... values) {
        return new ListGenerator<T>(asList(values));
    }

    public static <A, B> Generator<Tuple2<A, B>> tuples(Iterable<A> listOfAs,
            Iterable<B> listOfBs) {
        return new TupleGenerator<A, B>(listOfAs, listOfBs);
    }
}
