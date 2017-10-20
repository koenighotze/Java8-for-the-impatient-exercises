package org.koenighotze.chapter5;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.time.*;

import org.junit.*;

/**
 * @author David Schmitz
 */
public class Ex5_7 {
    @Test
    public void create_timeinterval() {
        LocalTime start = LocalTime.of(10, 00);
        LocalTime end = LocalTime.of(11, 0);

        TimeInterval timeInterval = TimeInterval.of(start, end);

        assertThat(timeInterval.startsAt()).isEqualTo(start);
        assertThat(timeInterval.endsAt()).isEqualTo(end);
    }

    @Test
    public void meetings_overlap() {
        LocalTime start = LocalTime.of(10, 00);
        LocalTime end = LocalTime.of(11, 0);

        TimeInterval firstMeeting = TimeInterval.of(start, end);
        TimeInterval secondMeeting = TimeInterval.of(start.plusMinutes(5), end.plusMinutes(30));

        assertThat(firstMeeting.overlapsWith(secondMeeting)).isTrue();
    }

    @Test
    public void meetings_dont_overlap() {
        LocalTime start = LocalTime.of(10, 00);
        LocalTime end = LocalTime.of(11, 0);

        TimeInterval firstMeeting = TimeInterval.of(start, end);
        TimeInterval secondMeeting = TimeInterval.of(end.plusMinutes(5), end.plusHours(1));

        assertThat(firstMeeting.overlapsWith(secondMeeting)).isFalse();
    }
}
