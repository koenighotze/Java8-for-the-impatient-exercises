package org.koenighotze.chapter2;

import org.junit.Test;

import java.util.stream.DoubleStream;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

/**
 * Created by dschmitz on 13.02.15.
 */
public class Ex210Test {
    public Double avg(Stream<Double>stream) {
//        long count = stream.count();
        // This wont work as the stream is already closed
//        Double reduce = stream.reduce(0d, (a, b) -> a + b);

        return stream.mapToDouble(d -> d.doubleValue()).average().getAsDouble();
    }


    @Test
    public void testCalc() throws Exception {
        Double res = avg(DoubleStream.of(0.5d, 1.3d, 2.0d, 6.1d).boxed());

        assertEquals(2.475d, res, 0.00001d);
    }


}
