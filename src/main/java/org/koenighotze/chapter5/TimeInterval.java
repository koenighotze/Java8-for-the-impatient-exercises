package org.koenighotze.chapter5;

import java.time.*;

/**
 * @author David Schmitz
 */
public class TimeInterval {
    private final LocalTime start;
    private final LocalTime end;

    private TimeInterval(LocalTime start, LocalTime end) {
        this.start = start;
        this.end = end;
    }

    public static TimeInterval of(LocalTime start, LocalTime end) {
        if (!start.isBefore(end)) {
            throw new IllegalArgumentException("start " + start + " should be before " + end);
        }

        return new TimeInterval(start, end);
    }

    public LocalTime startsAt() {
        return start;
    }

    public LocalTime endsAt() {
        return end;
    }

    public boolean overlapsWith(TimeInterval other) {
        if (other.start.isAfter(end)) {
            return false;
        }

        if (other.end.isBefore(start)) {
            return false;
        }

        return true;
    }
}
