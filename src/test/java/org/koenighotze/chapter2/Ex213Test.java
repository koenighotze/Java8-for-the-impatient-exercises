package org.koenighotze.chapter2;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.*;
import java.util.stream.*;

import org.junit.*;
/**
 * Created by dschmitz on 13.02.15.
 */
public class Ex213Test {
    private static List<String> words;

    @BeforeClass
    public static void setup() throws Exception {
        words = ResourceReader.getWordsFromBook();
    }


    @Test
    public void testUsingGroups() throws Exception {
        Stream<String> wordStream = words.stream();
        int[] expected = new int[] { 0, 1826, 4999, 7637, 6166, 3589 };
        assertThat(countShortWords(wordStream), is(equalTo(expected)));
    }

    private int[] countShortWords(Stream<String> wordStream) {
        Map<Integer, Long> collect = wordStream
                .filter(w -> w.length() < 6)
                .collect(groupingBy(String::length,
                        counting()
                ));

        int[] result = new int[collect.size() + 1];

        collect.keySet().stream().sorted().forEach(k -> result[k] = collect.get(k).intValue());

        return result;

    }
}
