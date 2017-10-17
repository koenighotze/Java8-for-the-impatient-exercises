package org.koenighotze.chapter5;

import java.time.*;

/**
 * @author David Schmitz
 */
public class ProgrammersDay {

    /*
     * The Day of the Programmer is an international professional day that is celebrated on the 256th
     * (hexadecimal 100th, or the 28th) day of each year (September 13 during common years and on September 12 in leap years).
     */
    public static LocalDate calculateProgrammersDay(int year) {
        return LocalDate.of(year, Month.JANUARY, 1).plus(Period.ofDays(255));
    }
}
