package org.koenighotze.chapter2;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.*;

import org.junit.*;

/**
 * Created by dschmitz on 13.02.15.
 */
public class Ex211Test {

    @Test
    @Ignore("must think harder")
    public void test() throws Exception {
        List<ArrayList<String>> initial = new ArrayList<>();
        initial.add(new ArrayList<>(Arrays.asList("foo", "bar", "baz")));
        initial.add(new ArrayList<>(Arrays.asList("foo2", "bar2", "baz2")));
        initial.add(new ArrayList<>(Arrays.asList("foo3", "bar3", "baz3")));

        ArrayList<String> result = collect(initial);

        assertThat(result, is(equalTo(new ArrayList<>(Arrays.asList("foo", "bar", "baz",
                "foo2", "bar2", "baz2","foo3", "bar3", "baz3")))));
    }



    private ArrayList<String> collect(List<ArrayList<String>> initial) {
//        // flatMap all array elems into one big stream
//        ArrayList<>
//        initial.stream().flatMap(a -> a.stream());
//
//        IntStream rangeOfIdx = IntStream.of(0, 1000);
//
//
//        ArrayList<String> result = new ArrayList<>();
//
//        rangeOfIdx.forEach(idx -> result.set(idx, data[idx]));


        return new ArrayList<>();
    }
}
