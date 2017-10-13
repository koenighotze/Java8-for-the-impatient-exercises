package org.koenighotze.chapter2;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

import java.util.stream.*;

import org.junit.*;

/**
 * Created by dschmitz on 13.02.15.
 */
public class Ex210Test {
    public Double avg(Stream<Double>stream) {
//        long count = stream.count();
        // This wont work as the stream is already closed
//        Double reduce = stream.reduce(0d, (a, b) -> a + b);

        return stream.mapToDouble(Double::doubleValue).average().getAsDouble();
    }


    @Test
    public void testCalc() throws Exception {
        Double res = avg(DoubleStream.of(0.5d, 1.3d, 2.0d, 6.1d).boxed());

        assertThat(res).isCloseTo(2.475d, within(0.00001d));
    }


}
