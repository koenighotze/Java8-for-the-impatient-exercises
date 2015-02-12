package org.koenighotze.chapter2;

import org.junit.Ignore;
import org.junit.Test;

import java.util.Iterator;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by dschmitz on 12.02.15.
 */
public class Ex28 {
    public static <T> Stream<T> zip(Stream<T> first, Stream<T> second) {
//        Iterator<T> firstiterator = first.iterator();
//        Iterator<T> seconditerator = second.iterator();

        return Stream.generate( () -> null);
    }


    @Test
    @Ignore("...must think about this one...")
    public void testSimpleSameSize() throws Exception {
        Stream<Integer> first = IntStream.range(0, 3).boxed();

        Stream<Integer> second = IntStream.range(11, 3).boxed();


        Stream<Integer> zipped = zip(first, second);

        final Integer[] expected = new Integer[] { 0, 11, 1, 12, 2, 13 };

        assertThat(zipped.toArray(Integer[]::new), is(equalTo(expected)));

    }
}
