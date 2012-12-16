package de.schauderhaft.rules.parameterized;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class CrossProductIterableTest {
    @Test
    public void twoEmptyIterablesProduceAnEmptyIterable() {
        CrossProductIterable<Object, Object> result = new CrossProductIterable<>(
                asList(), asList());

        for (Tuple2<Object, Object> r : result) {
            fail("there shouldn't be any results but we found: " + r);
        }
    }

    @Test
    public void anEmptyIterablesInTheFirstArgumentProduceAnEmptyIterable() {
        CrossProductIterable<String, Object> result = new CrossProductIterable<>(
                asList("a", "b"), asList());

        for (Tuple2<String, Object> r : result) {
            fail("there shouldn't be any results but we found: " + r);
        }
    }

    @Test
    public void anEmptyIterablesInTheSecondArgumentProduceAnEmptyIterable() {
        CrossProductIterable<Object, String> result = new CrossProductIterable<>(
                asList(), asList("a", "b"));

        for (Tuple2<Object, String> r : result) {
            fail("there shouldn't be any results but we found: " + r);
        }
    }

    @Test
    public void twoSingleElementArgsProduceAnEmptyIterable() {
        CrossProductIterable<String, String> result = new CrossProductIterable<>(
                asList("a"), asList("b"));

        check(result, new Tuple2<String, String>("a", "b"));
    }

    @Test
    public void aSingleElementAndAListProduceAList() {
        CrossProductIterable<String, String> result = new CrossProductIterable<>(
                asList("a"), asList("b", "c", "d"));

        check(result, new Tuple2<String, String>("a", "b"),
                new Tuple2<String, String>("a", "c"),
                new Tuple2<String, String>("a", "d"));
    }

    @Test
    public void aSingleNullAndAListProduceAList() {
        CrossProductIterable<String, String> result = new CrossProductIterable<>(
                asList((String) null), asList("b", "c", "d"));

        check(result, new Tuple2<String, String>(null, "b"),
                new Tuple2<String, String>(null, "c"),
                new Tuple2<String, String>(null, "d"));
    }

    @Test
    public void aListOfThreeAndAListOfTwoResultsInAListOfSix() {
        CrossProductIterable<String, String> result = new CrossProductIterable<>(
                asList("1", "2", "3"), asList("a", "b"));

        check(result, new Tuple2<String, String>("1", "a"),
                new Tuple2<String, String>("1", "b"),
                new Tuple2<String, String>("2", "a"),
                new Tuple2<String, String>("2", "b"),
                new Tuple2<String, String>("3", "a"),
                new Tuple2<String, String>("3", "b"));
    }

    @SafeVarargs
    private final <X> void check(Iterable<X> iter, X... tuples) {
        Set<X> actual = new HashSet<>();
        for (X x : iter) {
            assertFalse("the element " + x
                    + " is contained twice in the iterator.",
                    actual.contains(x));
            actual.add(x);
        }

        Set<X> expected = new HashSet<>(asList(tuples));

        assertThat(actual, equalTo(expected));
    }
}
