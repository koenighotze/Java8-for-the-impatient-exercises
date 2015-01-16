package org.koenighotze.chapter1;

/**
 * Created by dschmitz on 16.12.14.
 */
public class Exercise7 {

    public static void main(String... args) {
        Runnable a = () -> System.out.println("Runnable A");
        Runnable b = () -> System.out.println("Runnable B");

        andThen(a, b).run();
    }

    private static Runnable andThen(Runnable a, Runnable b) {
        return () -> {
            a.run();
            b.run();
        };
    }
}
