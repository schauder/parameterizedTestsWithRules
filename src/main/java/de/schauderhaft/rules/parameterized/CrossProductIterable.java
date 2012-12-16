package de.schauderhaft.rules.parameterized;

import java.util.Iterator;

public class CrossProductIterable<A, B> implements
        Iterable<de.schauderhaft.rules.parameterized.Tuple2<A, B>> {

    public class CrossProductIterator implements Iterator<Tuple2<A, B>> {

        private final Iterable<B> bs;
        private final Iterator<A> aIter;
        private Iterator<B> bIter;
        private A currentA;
        private boolean hasCurrentA;

        public CrossProductIterator(Iterable<A> as, Iterable<B> bs) {
            this.bs = bs;
            aIter = as.iterator();
            nextA();
        }

        private void nextA() {
            hasCurrentA = aIter.hasNext();
            if (hasCurrentA)
                currentA = aIter.next();
            bIter = bs.iterator();

        }

        @Override
        public boolean hasNext() {
            boolean bHasNext = bIter.hasNext();
            if (!bHasNext) {
                nextA();
            }
            return hasCurrentA && bIter.hasNext();
        }

        @Override
        public Tuple2<A, B> next() {
            return new Tuple2<A, B>(currentA, bIter.next());
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException(
                    "Can't remove from this iterator");
        }

    }

    private final Iterable<A> as;
    private final Iterable<B> bs;

    public CrossProductIterable(Iterable<A> as, Iterable<B> bs) {
        this.as = as;
        this.bs = bs;
    }

    @Override
    public Iterator<Tuple2<A, B>> iterator() {
        return new CrossProductIterator(as, bs);
    }

}
