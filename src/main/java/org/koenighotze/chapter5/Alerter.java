package org.koenighotze.chapter5;

import static java.util.stream.Collectors.toList;

import java.time.*;
import java.util.*;

/**
 * @author David Schmitz
 */
public class Alerter {
    public static List<ZonedDateTime> dueAppointments(ZonedDateTime now, List<ZonedDateTime> appointments) {
        return appointments.stream()
                           .filter(zdt -> zdt.isAfter(now) && zdt.isBefore(now.plusMinutes(60L)))
                           .collect(toList());
    }
}
