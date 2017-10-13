package org.koenighotze.chapter3;

import static java.lang.Thread.currentThread;
import static java.lang.Thread.sleep;
import static java.util.Arrays.asList;
import static java.util.concurrent.CompletableFuture.supplyAsync;
import static java.util.concurrent.TimeUnit.SECONDS;
import static java.util.stream.Collectors.toList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;

import org.junit.*;

/**
 * @author David Schmitz
 */
public class Ex3_22Test {

    private static void sleeper(TimeUnit unit, long duration) {
        try {
            sleep(unit.toMillis(duration));
        } catch (InterruptedException e) {
            currentThread().interrupt();
        }
    }

    @Test
    public void trying_flatmap() {
        // @formatter:off
        List<String> result = Stream.of("Foo", "Baz")
                .flatMap(s -> Stream.of(s, s.toLowerCase(), s.toUpperCase()))
                .collect(toList());
        // @formatter:on
        assertThat(result, is(equalTo(asList("Foo", "foo", "FOO", "Baz", "baz", "BAZ"))));
    }

    @Test
    public void trying_map() {
        // @formatter:off
        List<List<String>> result = Stream.of("Foo", "Baz")
                .map(s -> Stream.of(s, s.toLowerCase(), s.toUpperCase()))
                .map(stream -> stream.collect(toList()))
                .collect(toList());
        // @formatter:on
        assertThat(result, is(equalTo(asList(asList("Foo", "foo", "FOO"), asList("Baz", "baz", "BAZ")))));
    }

    @Test
    public void map_with_completable_future() throws ExecutionException, InterruptedException {
        CompletableFuture<Stream<String>> future = supplyAsync(() -> {
            sleeper(SECONDS, 1);
            // @formatter:off
            return
                Stream.of("Foo", "Quuz");
            // @formatter:on
        });

        CompletableFuture<Stream<Integer>> composed =
            future.thenComposeAsync(stream -> supplyAsync(() -> stream.map(String::length)));
        List<Integer> result = composed.get().collect(toList());
        assertThat(result, is(equalTo(asList(3, 4))));

    }
}
