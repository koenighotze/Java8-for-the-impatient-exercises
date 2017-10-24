package org.koenighotze.chapter5;

import static java.time.Month.OCTOBER;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import java.time.*;
import java.util.*;

import org.junit.*;

/**
 * @author David Schmitz
 */
public class Ex5_12 {
    @Test
    public void appointment_alert() {
        List<ZonedDateTime> appointments = Arrays.asList(
            ZonedDateTime.of(LocalDate.of(2016, OCTOBER, 10), LocalTime.of(14, 15), ZoneId.of("Asia/Dhaka")),
            ZonedDateTime.of(LocalDate.of(2016, OCTOBER, 10), LocalTime.of(3, 40), ZoneId.of("America/Chicago")),
            ZonedDateTime.of(LocalDate.of(2016, OCTOBER, 10), LocalTime.of(13, 10), ZoneId.of("Europe/Paris")),
            ZonedDateTime.of(LocalDate.of(2016, OCTOBER, 10), LocalTime.of(11, 10), ZoneId.of("Asia/Kolkata")));

        ZonedDateTime now = ZonedDateTime.of(LocalDate.of(2016, OCTOBER, 10), LocalTime.of(10, 10),
                                             ZoneId.of("Europe/Paris"));

        List<ZonedDateTime> due = Alerter.dueAppointments(now, appointments);

        assertThat(due).contains(
            ZonedDateTime.of(LocalDate.of(2016, OCTOBER, 10), LocalTime.of(14, 15), ZoneId.of("Asia/Dhaka")),
            ZonedDateTime.of(LocalDate.of(2016, OCTOBER, 10), LocalTime.of(3, 40), ZoneId.of("America/Chicago")));
    }

    @Test
    public void no_alert_if_list_is_empty() {
        List<ZonedDateTime> appointments = Collections.emptyList();

        List<ZonedDateTime> due = Alerter.dueAppointments(ZonedDateTime.now(), appointments);

        assertThat(due).isEmpty();

    }

    @Test
    public void no_alert_if_no_appointment_is_due() {
        List<ZonedDateTime> appointments = Arrays.asList(
            ZonedDateTime.of(LocalDate.of(2016, OCTOBER, 10), LocalTime.of(10, 10), ZoneId.of("Asia/Dhaka")), // UTC+6
            ZonedDateTime.of(LocalDate.of(2016, OCTOBER, 10), LocalTime.of(12, 10), ZoneId.of("America/Chicago")),
            // UTC-5 (DST)
            ZonedDateTime.of(LocalDate.of(2016, OCTOBER, 10), LocalTime.of(13, 10), ZoneId.of("Europe/Paris")), // 0
            ZonedDateTime.of(LocalDate.of(2016, OCTOBER, 10), LocalTime.of(15, 10),
                             ZoneId.of("Asia/Kolkata"))); // UTC+5:30

        ZonedDateTime now = ZonedDateTime.of(LocalDate.of(2016, OCTOBER, 10), LocalTime.of(10, 30),
                                             ZoneId.of("Europe/Paris"));

        List<ZonedDateTime> due = Alerter.dueAppointments(now, appointments);

        assertThat(due).isEmpty();
    }
}
