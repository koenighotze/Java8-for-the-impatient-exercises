package org.koenighotze.chapter5;

import java.time.*;
import java.time.temporal.*;
import java.util.function.*;

/**
 * @author David Schmitz
 */
public class Datefinder {
    public static TemporalAdjuster next(Predicate<LocalDate> predicate) {
        return w -> {
            if (w instanceof LocalDate) {
                LocalDate localDate = LocalDate.class.cast(w);

                do {
                    localDate = localDate.plusDays(1);
                } while (!predicate.test(localDate));

                return localDate;
            }
            throw new UnsupportedTemporalTypeException(w.getClass().getName());
        };
    }
}
