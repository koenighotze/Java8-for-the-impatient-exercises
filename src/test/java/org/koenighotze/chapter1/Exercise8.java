package org.koenighotze.chapter1;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dschmitz on 16.12.14.
 */
public class Exercise8 {

    public static void main(String... args) {
        String[] names = {"Peter", "Paul", "Mary"};

        List<Runnable> runners = new ArrayList<>();
        for (String name : names) {
            runners.add(() -> System.out.println(name));
        }


//      Cannot do it this way...its not final!
//        for (int i = 0; i < names.length; i++) {
//            runners.add(() -> System.out.println(names[i]));
//        }
        for (Runnable r : runners) {
            r.run();
        }



    }
}
