package org.koenighotze.chapter5;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.time.*;

import org.junit.*;

/**
 * @author David Schmitz
 */
public class Ex5_2 {
    @Test
    public void add_one_year() {
        LocalDate result = LocalDate.of(2000, 2, 29)
                                       .plusYears(1);

        assertThat(result).isEqualTo(LocalDate.of(2001, 2, 28));
    }
    
    @Test
    public void add_four_years() {
        LocalDate result = LocalDate.of(2000, 2, 29)
                                    .plusYears(4);

        assertThat(result).isEqualTo(LocalDate.of(2004, 2, 29));
    }
    
    @Test
    public void add_one_year_four_times() {
        LocalDate result = LocalDate.of(2000, 2, 29);
        for (int i = 1; i <= 4; i++) {
            result = result.plusYears(1);
        }

        assertThat(result).isEqualTo(LocalDate.of(2004, 2, 28));
    }
}
