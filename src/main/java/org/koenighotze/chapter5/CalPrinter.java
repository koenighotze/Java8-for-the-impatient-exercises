package org.koenighotze.chapter5;

import static java.time.Month.JANUARY;
import static java.time.format.TextStyle.FULL;
import static java.util.Locale.GERMAN;

import java.time.*;
import java.util.*;

/**
 * Excersise 5.4
 *
 * @author David Schmitz
 */
public class CalPrinter {

    private static final int MAX_WEEKS_PER_MONTH = 6;
    private static final int MAX_DAYS_PER_WEEK = 7;

    public static void main(String[] args) {
        Month month = Month.of(Integer.parseInt(args[0]));
        int year = Integer.parseInt(args[1]);

        LocalDate date = LocalDate.of(year, month, 1);

        do {
            int[][] m = createMonthPrintout(date);
            System.out.println(date.getMonth()
                                   .getDisplayName(FULL, GERMAN));
            printMonth(m);
            System.out.println();

            date = date.plusMonths(1);
        }
        while (!isNextYear(date));

    }

    private static boolean isNextYear(LocalDate date) {
        return date.getMonth() == JANUARY;
    }

    private static int[][] createMonthPrintout(LocalDate date) {
        int daysInMonth = date.getMonth()
                              .length(date.isLeapYear());

        int[][] m = initEmptyMonth();

        int weekOfMonth = 0;
        for (int i = 1; i <= daysInMonth; i++) {
            int dayOfWeek = date.getDayOfWeek()
                                .getValue() - 1;
            m[weekOfMonth][dayOfWeek] = i;

            if (isEndOfWeek(date)) {
                weekOfMonth++;
            }

            date = date.plusDays(1);
        }
        return m;
    }

    private static boolean isEndOfWeek(LocalDate date) {
        return DayOfWeek.SUNDAY == date.getDayOfWeek();
    }

    private static int[][] initEmptyMonth() {
        int[][] m = new int[MAX_WEEKS_PER_MONTH][MAX_DAYS_PER_WEEK];
        for (int i = 0; i < MAX_WEEKS_PER_MONTH; i++) {
            Arrays.fill(m[i], 0);
        }

        return m;
    }

    private static void printMonth(int[][] m) {
        System.out.println(" MO  DI  MI  DO  FR  SA  SO");
        for (int[] aM : m) {

            if (monthHasDaysInWeek(aM)) {
                printWeek(aM);
            }

            System.out.println();
        }
    }

    private static boolean monthHasDaysInWeek(int[] array) {
        return Arrays.stream(array)
                     .anyMatch(v -> v > 0);
    }

    private static void printWeek(int[] ints) {
        for (int anInt : ints) {
            String v = anInt == 0 ? " -- " : String.format(" %2d ", anInt);

            System.out.print(v);
        }
    }
}
