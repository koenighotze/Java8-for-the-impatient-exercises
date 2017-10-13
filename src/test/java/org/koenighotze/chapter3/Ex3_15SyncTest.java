package org.koenighotze.chapter3;

import static java.util.Objects.requireNonNull;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.function.*;

import org.junit.*;

/**
 * @author David Schmitz
 */
public class Ex3_15SyncTest {
    public static class StringHandler {
        private final String source;
        private Function<String, String> ops = UnaryOperator.identity();

        private StringHandler(String source) {
            this.source = source;
        }

        public static StringHandler of(String source) {
            return new StringHandler(requireNonNull(source));
        }

        public StringHandler then(UnaryOperator<String> operation) {
            ops = ops.andThen(operation);
            return this;
        }

        public String apply() {
            return ops.apply(source);
        }
    }

    @Test
    public void no_op() {
        assertThat(StringHandler.of("a simple sentence").apply(), is(equalTo("a simple sentence")));
    }

    @Test
    public void empty_source() {
        assertThat(StringHandler.of("").apply(), is(equalTo("")));
    }

    @Test
    public void single_transform() {
        assertThat(StringHandler.of("Qux").then(String::toLowerCase).apply(), is(equalTo("qux")));
    }

    @Test
    public void multi_transform() {
        // @formatter:off
        assertThat(StringHandler.of("Qux")
            .then(String::toLowerCase)
            .then(s -> s.substring(1, 3))
            .apply(), is(equalTo("ux")));
        // @formatter:on
    }

}
