package org.koenighotze.chapter3;

import org.junit.Test;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Comparator;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.TEN;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author dschmitz
 */
public class Ex3_9Test {
    @SuppressWarnings("unchecked")
    private static Object getFieldValue(Object o, String f) {
        try {
            Field declaredField = o.getClass().getDeclaredField(f);
            declaredField.setAccessible(true);
            return declaredField.get(o);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

    public static Comparator<Object> lexicographicComparator(String... fields) {
        Comparator<Object> result = Comparator.comparing((o) -> {
            Object val = getFieldValue(o, fields[0]);
            return (Comparable) val;
            // cast needed otherwise crazy inference bullcrap
        }, Comparator.<Comparable>naturalOrder());

        if (fields.length > 1) {
            result.thenComparing(lexicographicComparator(Arrays.copyOfRange(fields, 1, fields.length)));
        }

        return result;
    }

    @Test
    public void naive_test() {
        Comparator<Object> comparator = lexicographicComparator("name", "lastname");

        assertEquals(comparator.compare(new SimpleA("foo", "", null), new SimpleB("foo", "", null)), 0);
    }

    @Test
    public void naive_non_empty_equal() {

        Comparator<Object> comparator = lexicographicComparator("name", "lastname");

        assertEquals(comparator.compare(new SimpleA("Foo", "Bar", ONE), new SimpleB("Foo", "bar", TEN)), 0);
    }

    @Test
    public void naive_non_empty_not_equal() {

        Comparator<Object> comparator = lexicographicComparator("name", "lastname");

        assertEquals(comparator.compare(new SimpleA("Foo", "Bar", ONE), new SimpleB("Foo", "Bar", TEN)), 0);
    }

    @Test
    public void ordering() {
        Comparator<Object> comparator = lexicographicComparator("name", "qux");
        SimpleA simpleA = new SimpleA("nameA", "pre", null);
        SimpleA simpleB = new SimpleA("nameB", "pre", null);

        // if we compared all fields, then we would fail with a NPE!

        assertTrue(comparator.compare(simpleA, simpleB) < 0);
    }

    @SuppressWarnings("unused")
    private static class SimpleA {
        private String name;
        private String lastname;
        private BigDecimal qux;

        public SimpleA(String name, String lastname, BigDecimal qux) {
            this.name = name;
            this.lastname = lastname;
            this.qux = qux;
        }
    }

    @SuppressWarnings("unused")
    private static class SimpleB {
        private String name;
        private String lastname;
        private BigDecimal bar;

        public SimpleB(String name, String lastname, BigDecimal bar) {
            this.name = name;
            this.lastname = lastname;
            this.bar = bar;
        }
    }
}
