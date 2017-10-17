package org.koenighotze.chapter5;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.time.*;

import org.junit.*;

/**
 * @author David Schmitz
 */
public class Ex5_1 {
    @Test
    public void programmersDay_leapyear() {
        LocalDate programmersDay = ProgrammersDay.calculateProgrammersDay(2016);

        assertThat(programmersDay).isEqualTo(LocalDate.of(2016, 9, 12));
    }

    @Test
    public void programmersDay_standardyear() {
        LocalDate programmersDay = ProgrammersDay.calculateProgrammersDay(2015);

        assertThat(programmersDay).isEqualTo(LocalDate.of(2015, 9, 13));
    }
}
