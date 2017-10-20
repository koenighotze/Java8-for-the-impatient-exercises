package org.koenighotze.chapter5;

import static java.time.Month.SEPTEMBER;

import java.time.*;
import java.time.temporal.*;

import org.junit.*;

/**
 * @author David Schmitz
 */
public class Ex5_5 {
    @Test
    public void days_alive() {
        LocalDate today = LocalDate.now();
        LocalDate birthday = LocalDate.of(1976, SEPTEMBER, 8);

        Period alive = Period.between(birthday, today);
        System.out.println(String.format("You have been alive for %d years %d months %d days", alive.getYears(), alive.getMonths(), alive.getDays()));

        long daysAlive = birthday.until(today, ChronoUnit.DAYS);
        System.out.println("This means, you are alive for " + daysAlive + " days.");
    }
}
