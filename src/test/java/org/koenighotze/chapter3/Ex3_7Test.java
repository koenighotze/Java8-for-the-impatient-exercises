package org.koenighotze.chapter3;

import org.junit.Test;

import java.util.Comparator;
import java.util.function.Function;

import static org.junit.Assert.assertTrue;

/**
 * @author dschmitz
 * @date 17/06/15.
 */
public class Ex3_7Test {

    public static class Builder {
        private Comparator<String> current = Comparator.naturalOrder();

        public Builder withArgumentTransformer(Function<String, String> transformer) {
            current = (s1, s2) -> transformer.apply(s1).compareTo(transformer.apply(s2));
            return this;
        }

        public Comparator<String> comparator() {
            return current;
        }
    }

    @Test
    public void normal() {

        Comparator<String> normal = new Builder().comparator();

        assertTrue(normal.compare("Foo", "Qux") < 0);
    }

    @Test
    public void reversed() {
        Comparator<String> normal = new Builder().comparator().reversed();

        assertTrue(normal.compare("Foo", "Qux") > 0);
    }

    @Test
    public void caseInsensitive() {
        Comparator<String> normal = new Builder().withArgumentTransformer(String::toLowerCase).comparator();

        assertTrue(normal.compare("FOO", "Foo") == 0);
    }

    @Test
    public void caseSensitive() {
        Comparator<String> normal = new Builder().comparator();

        assertTrue(normal.compare("FOO", "Foo") < 0);
    }

    @Test
    public void spaceSensitive() {
        Comparator<String> normal = new Builder().comparator();

        assertTrue(normal.compare("F o o", "Foo") < 0);
    }

    @Test
    public void spaceInsensitive() {
        Comparator<String> normal = new Builder().withArgumentTransformer(s -> s.replaceAll("\\s+", "")).comparator();

        assertTrue(normal.compare("F o o", "Foo") == 0);
    }
}
