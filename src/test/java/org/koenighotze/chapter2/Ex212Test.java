package org.koenighotze.chapter2;

import static java.util.stream.Stream.of;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.*;
import java.util.concurrent.atomic.*;
import java.util.stream.*;

import org.junit.*;

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

        return Arrays.stream(count).mapToInt(AtomicInteger::get).toArray();
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
