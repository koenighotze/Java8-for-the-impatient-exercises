package org.koenighotze.chapter2;

import org.junit.Test;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Stream.of;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by dschmitz on 13.02.15.
 */
public class Ex212Test {


    @Test
    public void testArray() throws Exception {
        Stream<String> shortWords = of("oofof", "ds", "dsadsadasads", "ssdaads", "asdasdda");

        int[] expected = new int[] { 0, 0, 1, 0, 0, 1 };

        assertThat(countShortWordsArray(shortWords), is(equalTo(expected)));
    }

    private int[] countShortWordsArray(Stream<String> shortWords) {
        AtomicInteger[] count = new AtomicInteger[6];
        IntStream.range(0, 6).forEach(i -> count[i] = new AtomicInteger());

        shortWords.parallel().filter(w -> w.length() < 6).forEach(w -> count[w.length()].incrementAndGet());

        return Arrays.stream(count).mapToInt(a -> a.get()).toArray();
    }


    @Test
    public void test() throws Exception {
        Stream<String> shortWords = of("oofof", "ds", "dsadsadasads", "ssdaads", "asdasdda");

        assertThat(countShortWords(shortWords), is(equalTo(2)));
    }

    private int countShortWords(Stream<String> shortWords) {
        AtomicInteger count = new AtomicInteger();

        shortWords.parallel().forEach(w -> {
            if (w.length() < 6)
                count.incrementAndGet();
        });

        return count.get();
    }


}
