package org.koenighotze.chapter3;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.*;
import java.util.function.*;

import org.junit.*;

/**
 * @author David Schmitz
 */
public class Ex3_23 {

    private static class Pair<T> {
        private final T first;
        private final T second;

        private Pair(T first, T second) {
            this.first = first;
            this.second = second;
        }

        private static <T> Pair<T> of(T first, T second) {
            return new Pair<>(first, second);
        }

        <G> Pair<G> map(Function<T, G> mapper) {
            return Pair.of(mapper.apply(first), mapper.apply(second));
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Pair<?> pair = (Pair<?>) o;
            return Objects.equals(first, pair.first) && Objects.equals(second, pair.second);
        }

        @Override
        public int hashCode() {
            return Objects.hash(first, second);
        }

        @Override
        public String toString() {
            return "Pair{" + "first=" + first + ", second=" + second + '}';
        }
    }

    @Test
    public void map_on_pair() {
        Pair<String> pair = Pair.of("First", "Second");

        Pair<String> result = pair.map(String::toUpperCase);
        assertThat(result).isEqualTo(Pair.of("FIRST", "SECOND"));
    }

    @Test
    public void flatmap_on_pair() {
        // makes no sense for Pairs.

        /*
        Pair(a, b)
        a -> Pair(x, y)
        b -> Pair(v, w)

        how to construct Pair( x compose v, y compose w)

        we need a flatmap function and a compose function
         */

        assertThat(true).isTrue();
    }
}
