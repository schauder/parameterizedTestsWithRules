package de.schauderhaft.rules.parameterized;

public class Tuple2<A, B> {
    @Override
    public String toString() {
        return "<[" + _1 + ", " + _2 + "]>";
    }

    public final A _1;
    public final B _2;

    public Tuple2(A anA, B aB) {
        _1 = anA;
        _2 = aB;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((_1 == null) ? 0 : _1.hashCode());
        result = prime * result + ((_2 == null) ? 0 : _2.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Tuple2<?, ?> other = (Tuple2<?, ?>) obj;
        if (_1 == null) {
            if (other._1 != null)
                return false;
        } else if (!_1.equals(other._1))
            return false;
        if (_2 == null) {
            if (other._2 != null)
                return false;
        } else if (!_2.equals(other._2))
            return false;
        return true;
    }

}
