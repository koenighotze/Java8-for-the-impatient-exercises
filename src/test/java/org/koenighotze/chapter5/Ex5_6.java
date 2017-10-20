package org.koenighotze.chapter5;

import static java.time.Month.JANUARY;

import java.time.*;
import java.time.temporal.*;

import org.junit.*;

/**
 * @author David Schmitz
 */
public class Ex5_6 {
    @Test
    public void jasons_back() {
        final LocalDate twentiethCentury = LocalDate.of(2000, JANUARY, 1);
        LocalDate date = LocalDate.of(1900, JANUARY, 1)
                                  .with(TemporalAdjusters.nextOrSame(DayOfWeek.FRIDAY));

        while (date.isBefore(twentiethCentury)) {
            if (date.getDayOfMonth() == 13) {
                System.out.println(date);
            }

            date = date.plusWeeks(1);
        }
    }
}
