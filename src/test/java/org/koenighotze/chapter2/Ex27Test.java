package org.koenighotze.chapter2;

import org.junit.Test;
import org.junit.Ignore;

import java.time.Instant;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by dschmitz on 11.02.15.
 */
public class Ex27Test {
    // well...what happens IF the stream is infinite? BOOM!
    public static <T> boolean isFinite(Stream<T> stream) {
        stream.forEach( c -> {});
        return true;
    }

    @Test
    public void testWithFiniteStream() throws Exception {
        assertTrue(isFinite(IntStream.range(0, 10).boxed()));
    }
    // will not run with infinite stream

    @Test(timeout = 10000L)
    @Ignore("will not run with infinite stream")
    public void testWithInfineStream() throws Exception {
        assertFalse(isFinite(Stream.generate(() -> "" + Instant.now())));
    }
}
