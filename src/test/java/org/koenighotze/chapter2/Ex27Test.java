package org.koenighotze.chapter2;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.time.*;
import java.util.stream.*;

import org.junit.*;

/**
 * Created by dschmitz on 11.02.15.
 */
public class Ex27Test {
    // well...what happens IF the stream is infinite? BOOM!
    @SuppressWarnings("SameReturnValue")
    private static <T> boolean isFinite(Stream<T> stream) {
        stream.forEach( c -> {});
        return true;
    }

    @Test
    public void testWithFiniteStream() throws Exception {
        assertThat(isFinite(IntStream.range(0, 10).boxed())).isTrue();
    }
    // will not run with infinite stream

    @Test(timeout = 10000L)
    @Ignore("will not run with infinite stream")
    public void testWithInfineStream() throws Exception {
        assertThat(isFinite(Stream.generate(() -> "" + Instant.now()))).isFalse();
    }
}
