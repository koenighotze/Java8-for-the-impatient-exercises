package org.koenighotze.chapter2;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertNotNull;

/**
 * Created by dschmitz on 16.01.15.
 */
public class Ex22 {
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
