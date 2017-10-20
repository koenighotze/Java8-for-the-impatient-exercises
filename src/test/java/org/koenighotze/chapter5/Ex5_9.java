package org.koenighotze.chapter5;

import static java.util.concurrent.TimeUnit.HOURS;

import java.time.*;

import org.junit.*;

/**
 * @author David Schmitz
 */
public class Ex5_9 {
    @Test
    public void all_todays_offsets_for_all_timezones() {
        Instant now = Instant.now();
        ZoneId.getAvailableZoneIds()
              .stream()
              .map(ZoneId::of)
              .filter(zoneId -> 0 != now.atZone(zoneId)
                                        .getOffset()
                                        .getTotalSeconds() % HOURS.toSeconds(1))
              .forEach(zoneId -> {
                  ZoneOffset offset = now.atZone(zoneId)
                                         .getOffset();
                  System.out.println(zoneId + " has offset " + offset);
              });
    }
}
