package org.koenighotze.chapter5;

import java.time.*;

import org.junit.*;

/**
 * @author David Schmitz
 */
public class Ex5_10 {
    @Test
    public void flightduration_LA_to_FRA() {
        ZonedDateTime takeoffTime = ZonedDateTime.of(LocalDate.of(2011, 2, 2), LocalTime.of(15, 5),
                                            ZoneId.of("America/Los_Angeles"));

        System.out.println("Takes of at: " + takeoffTime.toLocalDateTime());

        ZonedDateTime landingTime = takeoffTime.plusHours(10)
                                               .plusMinutes(5)
                                               .withZoneSameInstant(ZoneId.of("Europe/Paris"));

        System.out.println("Lands at: " + landingTime.toLocalDateTime());

    }
}
