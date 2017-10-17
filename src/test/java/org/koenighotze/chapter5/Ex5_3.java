package org.koenighotze.chapter5;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.time.*;

import org.junit.*;

/**
 * @author David Schmitz
 */
public class Ex5_3 {
    @Test
    public void adjuster() {
        LocalDate today = LocalDate.of(2017, 10, 10);

        LocalDate result = today.with(Datefinder.next(w -> w.getDayOfWeek() == DayOfWeek.FRIDAY));

        assertThat(result).isEqualTo(LocalDate.of(2017, 10, 13));
    }
}
