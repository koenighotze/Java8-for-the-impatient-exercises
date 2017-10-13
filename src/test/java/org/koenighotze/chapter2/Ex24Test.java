package org.koenighotze.chapter2;

import java.util.*;
import java.util.stream.*;

import org.junit.*;

/**
 * Created by dschmitz on 10.02.15.
 */
public class Ex24Test {


    @Test
    public void testEx24() throws Exception {
        int[] vals = new int [] { 1, 2, 3 , 4};

        Stream.of(vals).forEach(e -> System.out.println(Arrays.toString(e)));

        Arrays.stream(vals).forEach(System.out::println);
    }
}
