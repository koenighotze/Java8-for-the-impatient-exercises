package org.koenighotze.chapter3;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.*;
import java.util.function.*;

import org.junit.*;

/**
 * @author David Schmitz
 */
public class Ex3_15AsyncTest {
    public static class AsyncStringHandler {
        private final String source;
        private final List<UnaryOperator<String>> ops;

        private AsyncStringHandler(String source) {
            this.source = source;
            this.ops = new LinkedList<>();
        }

        public static AsyncStringHandler of(String source) {
            return new AsyncStringHandler(requireNonNull(source));
        }

        public AsyncStringHandler then(UnaryOperator<String> operation) {
            ops.add(operation);
            return this;
        }

        public List<String> apply() {
            if (ops.isEmpty()) {
                return singletonList(source);
            }

            return ops.parallelStream().map(op -> op.apply(source)).collect(toList());
        }
    }

    @Test
    public void no_op() {
        assertThat(AsyncStringHandler.of("a simple sentence").apply(), is(equalTo(singletonList("a simple sentence"))));
    }

    @Test
    public void empty_source() {
        assertThat(AsyncStringHandler.of("").apply(), is(equalTo(singletonList(""))));
    }

    @Test
    public void single_transform() {
        assertThat(AsyncStringHandler.of("Qux").then(String::toLowerCase).apply(), is(equalTo(singletonList("qux"))));
    }

    @Test
    public void multi_transform() {
        // @formatter:off
        assertThat(AsyncStringHandler.of("Qux")
            .then(String::toLowerCase)
            .then(s -> s.substring(1, 3))
            .apply(), is(equalTo(asList("qux", "ux"))));
        // @formatter:on
    }

}
