package org.koenighotze.chapter5;

import static java.util.concurrent.TimeUnit.HOURS;
import static java.util.concurrent.TimeUnit.SECONDS;

import java.time.*;
import java.time.temporal.*;

import org.junit.*;

/**
 * @author David Schmitz
 */
public class Ex5_11 {
    @Test
    public void flightduration_FRA_to_LA() {
        ZonedDateTime takeoffTime = ZonedDateTime.of(LocalDate.now(), LocalTime.of(14, 5),
                                                     ZoneId.of("Europe/Paris"));
        System.out.println("Take off at: " + takeoffTime.toLocalDateTime());

        ZonedDateTime landingTime = ZonedDateTime.of(LocalDate.now(), LocalTime.of(16, 40), ZoneId.of("America/Los_Angeles"));

        Duration flightDuration = Duration.between(takeoffTime, landingTime);

        long duration = flightDuration.get(ChronoUnit.SECONDS);
        long hours = SECONDS.toHours(duration);
        duration = duration - HOURS.toSeconds(hours);
        long minutes = SECONDS.toMinutes(duration);

        System.out.println("Flight took " + flightDuration);
        System.out.println(String.format("Flight takes %d hours and %d minutes", hours, minutes));
    }
}
