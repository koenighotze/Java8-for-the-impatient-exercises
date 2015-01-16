package org.koenighotze.chapter1;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Created by dschmitz on 16.12.14.
 */
public class Exercise9 {

    public static interface Collection2<E> extends Collection<E> {
        default void forEachIf(Consumer<E> action, Predicate<E> filterPred) {
            stream()
                    .filter(filterPred)
                    .forEach(action);
        };
    }

    public static class ExLinkedList<E> extends LinkedList<E> implements Collection2<E> {

    }

    public static void main(String... args) {
        Collection2<String> l = new ExLinkedList<>();
        l.addAll(Arrays.asList("foo", "Bar", "Fam"));
        l.forEachIf(s -> System.out.println(s),
                s -> s.toLowerCase().startsWith("f"));
    }
}
