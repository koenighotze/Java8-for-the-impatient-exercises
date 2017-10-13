package org.koenighotze.chapter3;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.*;
import java.util.function.*;

import org.junit.*;

/**
 * @author dschmitz
 */
public class Ex3_7Test {

    static class Builder {
        private Comparator<String> current = Comparator.naturalOrder();

        Builder withArgumentTransformer(Function<String, String> transformer) {
            current = Comparator.comparing(transformer);
            return this;
        }

        Comparator<String> comparator() {
            return current;
        }
    }

    @Test
    public void normal() {

        Comparator<String> normal = new Builder().comparator();

        assertThat(normal.compare("Foo", "Qux") < 0).isTrue();
    }

    @Test
    public void reversed() {
        Comparator<String> normal = new Builder().comparator()
                                                 .reversed();

        assertThat(normal.compare("Foo", "Qux") > 0).isTrue();
    }

    @Test
    public void caseInsensitive() {
        Comparator<String> normal = new Builder().withArgumentTransformer(String::toLowerCase)
                                                 .comparator();

        assertThat(normal.compare("FOO", "Foo") == 0).isTrue();
    }

    @Test
    public void caseSensitive() {
        Comparator<String> normal = new Builder().comparator();

        assertThat(normal.compare("FOO", "Foo") < 0).isTrue();
    }

    @Test
    public void spaceSensitive() {
        Comparator<String> normal = new Builder().comparator();

        assertThat(normal.compare("F o o", "Foo") < 0).isTrue();
    }

    @Test
    public void spaceInsensitive() {
        Comparator<String> normal = new Builder().withArgumentTransformer(s -> s.replaceAll("\\s+", ""))
                                                 .comparator();

        assertThat(normal.compare("F o o", "Foo") == 0).isTrue();
    }
}
