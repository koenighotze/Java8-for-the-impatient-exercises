package org.koenighotze.chapter2;

import org.junit.Test;

import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

/**
 * Created by dschmitz on 10.02.15.
 */
public class Ex24Test {


    @Test
    public void testEx24() throws Exception {
        int[] vals = new int [] { 1, 2, 3 , 4};

        Stream.of(vals).forEach(e -> System.out.println(e));

        Arrays.stream(vals).forEach(e -> System.out.println(e));
    }
}
