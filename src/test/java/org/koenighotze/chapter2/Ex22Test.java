package org.koenighotze.chapter2;

import java.util.*;

import org.junit.*;

/**
 * Created by dschmitz on 16.01.15.
 */
public class Ex22Test {
    private List<String> words;

    @Before
    public void setup() throws Exception {
        this.words = ResourceReader.getWordsFromBook();
    }

    @Test
    public void testEx22() throws Exception {
        this.words.stream()
                .filter((s) -> {
                    System.out.println("Filtering " + s);
                    return s.length() > 3;
                })
                .limit(5)
                .forEach((s) -> System.out.println("Found " + s));
    }
}
