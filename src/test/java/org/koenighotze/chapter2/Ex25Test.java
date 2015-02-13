package org.koenighotze.chapter2;

import org.junit.Test;

import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * Created by dschmitz on 10.02.15.
 */
public class Ex25Test {

    private Stream<Long> createStream(long seed, long a, long m, long c) {

        return Stream.iterate(seed, n -> (a * n + c) % m);

    }

    @Test
    public void testInfiniteStream() throws Exception {
        createStream(5, 33211,  (long) Math.pow(2, 48), 11).limit(10)
                .forEach(s -> System.out.println(s));
    }

}
