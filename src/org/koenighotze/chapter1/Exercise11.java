package org.koenighotze.chapter1;

/**
 * Created by dschmitz on 16.12.14.
 */
public class Exercise11 implements I, J{
    @Override
    public void f() {
        System.out.println("Exercise11.F");
    }

    public static void main(String... args) {
        I.f();
        new Exercise11().f();
    }
}
