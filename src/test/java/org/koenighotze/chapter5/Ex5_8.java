package org.koenighotze.chapter5;

import java.time.*;

import org.junit.*;

/**
 * @author David Schmitz
 */
public class Ex5_8 {
    @Test
    public void all_todays_offsets_for_all_timezones() {
        Instant now = Instant.now();
        ZoneId.getAvailableZoneIds()
            .stream()
              .map(ZoneId::of)
              .map(id -> now.atZone(id).getOffset())
              .distinct()
              .sorted()
              .forEach(System.out::println);
    }
}
