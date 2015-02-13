package org.koenighotze.chapter2;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertNotNull;

/**
 * Created by dschmitz on 16.01.15.
 */
public class ResourceReader {
    public static List<String> getLargeWordsFromBook() throws Exception {
        URL alice = Ex21Test.class.getResource("/pg2600.txt");
        assertNotNull(alice);
        String contents = new String(Files.readAllBytes(
                Paths.get(alice.toURI())), StandardCharsets.UTF_8);

        // make string laaaaaarge
        for (int i = 0; i < 5; i++)
            contents += contents;

        return Arrays.asList(contents.split("[\\P{L}]+"));

    }

    public static List<String> getWordsFromBook() throws Exception {
        URL alice = Ex21Test.class.getResource("/alice.txt");
        assertNotNull(alice);
        String contents = new String(Files.readAllBytes(
                Paths.get(alice.toURI())), StandardCharsets.UTF_8);
        return Arrays.asList(contents.split("[\\P{L}]+"));
    }

}
