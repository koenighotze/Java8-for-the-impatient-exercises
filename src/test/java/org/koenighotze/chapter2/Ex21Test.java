package org.koenighotze.chapter2;

import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;

import org.junit.*;

/**
 * Created by dschmitz on 16.01.15.
 */
public class Ex21Test {
    private static List<String> words;

    @BeforeClass
    public static void setup() throws Exception {
        words = ResourceReader.getWordsFromBook();
    }


    @Test
    public void testOriginal() throws Exception {
        measure("Iterative", (l) -> {
            long count = 0;
            for (String w : words) {
                if (w.length() > 10) {
                    count++;
                }
            }
            return count;
        });
    }


    @Test
    public void testManualParallel() throws Exception {
        // Achtung: garstiger code ahead!
        final int availableProcessors = Runtime.getRuntime().availableProcessors();
        final long size = words.size();
        final int chunkSize = (int) (size / availableProcessors);

        System.out.println("Split word list into " + availableProcessors + " chunks of " + chunkSize + " words");

        List<Callable<Long>> tasks = prepareTaskList(availableProcessors, size, chunkSize);

        long start = System.currentTimeMillis();

        ExecutorService executorService = Executors.newFixedThreadPool(availableProcessors);
        List<Future<Long>> futures = executorService.invokeAll(tasks);
        executorService.shutdown();

        long end = System.currentTimeMillis();
        long res = futures.stream().mapToLong((f) -> {
            try {
                return f.get();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).sum();

        System.out.println(" Manual Threads: millis: " + (end - start));
    }

    private List<Callable<Long>> prepareTaskList(int availableProcessors, long size, int chunkSize) {
        List<Callable<Long>> tasks = new LinkedList<>();

        for (int chunk = 0; chunk < availableProcessors; chunk ++) {
            final int from = chunk * chunkSize;
            int to = chunk * chunkSize +  chunkSize;

            if (to > size) to = (int) size;

            final List<String> chunkData = words.subList(from, to);

            tasks.add(() -> {
                long count = 0;
                for (String w : chunkData) {
                    if (w.length() > 10) {
                        count++;
                    }
                }
                return count;
            });
        }
        return tasks;

    }

    private void measure(String method, Function<List<String>, Long> func) {
        long start = System.currentTimeMillis();

        long count = func.apply(words);
        long duration = System.currentTimeMillis() - start;
        System.out.println(method + " took " + duration + " millis");

    }

    @Test
    public void testStream() throws Exception {
        measure("Stream", (list) -> list.stream().filter((s) -> s.length() > 10).count());
    }


    @Test
    public void testParallelStream() throws Exception {
        measure("Parallel stream", (l) -> l.parallelStream().filter((s) -> s.length() > 10).count());
    }

}
