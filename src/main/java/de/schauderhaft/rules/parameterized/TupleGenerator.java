package de.schauderhaft.rules.parameterized;

import org.junit.runner.Description;
import org.junit.runners.model.Statement;


public class TupleGenerator<A, B> implements Generator<Tuple2<A, B>> {

    private final Iterable<A> as;
    private final Iterable<B> bs;
    private final ValueContainer<Tuple2<A, B>> currentValue = new ValueContainer<>();
    private final AccessibleErrorCollector errorCollector = new AccessibleErrorCollector();

    public TupleGenerator(Iterable<A> as, Iterable<B> bs) {
        this.as = as;
        this.bs = bs;
    }

    @Override
    public Statement apply(Statement test, Description arg1) {
        return new RepeatedStatement<Tuple2<A, B>>(test, new SyncingIterable(
                new CrossProductIterable<>(as, bs), currentValue),
                errorCollector);
    }

    @Override
    public Tuple2<A, B> value() {
        return currentValue.get();
    }

}
