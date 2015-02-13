package org.koenighotze.chapter2;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by dschmitz on 12.02.15.
 */
public class Ex29Test {

    private Stream<ArrayList<String>> stream;
    private ArrayList<String> expected = new ArrayList<>(Arrays.asList("Die", "For", "tu", "na"));

    public static <T> ArrayList<T> join1(Stream<ArrayList<T>> stream) {

        return stream.reduce(new ArrayList<>(), (a, b) -> {
            a.addAll(b);
            return a;
        });
    }

    public static <T> ArrayList<T> join2(Stream<ArrayList<T>> stream) {
        return stream
                .reduce((a, b) -> {
                        ArrayList<T> l = new ArrayList<>();
                        l.addAll(a);
                        l.addAll(b);
                        return l;
                    }
                )
                // if empty
                .orElse(new ArrayList<>());
    }


    public static <T> ArrayList<T> join3(Stream<ArrayList<T>> stream) {
        BiFunction<ArrayList<T>, ? super ArrayList<T>, ArrayList<T>> acc = (a, b) ->
        {
            ArrayList<T> result = new ArrayList<>(a);

            result.addAll(b);

            return result;
        };
        BinaryOperator<ArrayList<T>> comb = (a, b) -> {
            a.addAll(b);
            return a;
        };
        return stream.reduce(new ArrayList<>(), acc, comb);

    }

    @Before
    public void setup() {
        // mae... returns List...
        ArrayList<String> first = new ArrayList<>(Arrays.asList("Die", "For"));
        ArrayList<String> second = new ArrayList<>(Arrays.asList("tu", "na"));

        this.stream = Stream.of(first, second);
    }

    @Test
    public void testJoiner1() throws Exception {
        ArrayList<String> result = join1(this.stream);
        assertThat(result, is(equalTo(this.expected)));
    }

    @Test
    public void testJoiner2() throws Exception {
        ArrayList<String> result = join2(this.stream);
        assertThat(result, is(equalTo(this.expected)));
    }

    @Test
    public void testJoiner3() throws Exception {
        ArrayList<String> result = join3(this.stream);
        assertThat(result, is(equalTo(this.expected)));
    }
}
