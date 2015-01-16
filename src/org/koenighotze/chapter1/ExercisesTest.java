package org.koenighotze.chapter1;

import org.junit.Test;

import java.io.File;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Comparator.*;
/**
 * Created by dschmitz on 15.12.14.
 */
public class ExercisesTest {


    @Test
    public void exercise2() {
        File file = new File("/Users/dschmitz");

        System.out.println(Arrays.toString(file.listFiles(f -> f.isDirectory())));

        System.out.println(Arrays.toString(file.listFiles(File::isDirectory)));
    }


    @Test
    public void exercise3() {
        String extension = ".sh";
        File file = new File("/Users/dschmitz/bin");

        Stream.of(file.listFiles(f -> f.getName().endsWith(extension))).forEach(f -> System.out.println(f.getName()));
    }


    @Test
    public void exercise4() {
        File file = new File("/Users/dschmitz/Downloads");


//        Comparator<File> comparator = new Comparator<File>() {
//            @Override
//            public int compare(File o1, File o2) {
//                return o1.getName().compareTo(o2.getName());
//            }
//        };
//
//        Stream.of(file.listFiles())
//                .collect(groupingBy(File::isDirectory))
//                .forEach((isDir, list) ->  {
//                            list.sort(comparator);
//                            System.out.println(list);
//                        }
//                );



//        Stream.of(file.listFiles())
//                .sorted((f, g) -> g.getName().compareTo(g.getName()))
//                //.collect(groupingBy(File::isDirectory));
//                .sorted((f, g) -> {
//                   if (f.isDirectory()) {
//                       return g.isDirectory() ? 0 : -1;
//                   }
//                   else {
//                       return g.isDirectory() ? 1 : 0;
//                   }
//                })
//                .forEach(System.out::println);

//        Stream.of(file.listFiles())
//                .sorted(
//                        comparing(File::getName)
//                                .thenComparing(File::isDirectory))
//                .forEach(System.out::println);


        Stream.of(file.listFiles())
                .sorted(comparing(File::isDirectory).thenComparing(File::getName))
                .forEach(System.out::println);

//        Stream.of(file.listFiles())
//                .sorted(comparing(File::getName))
//                .sorted(comparing(File::isDirectory))
//                .forEach(System.out::println);

    }

    @Test
    public void exercise5() {
        new Thread(uncheck(() -> Thread.sleep(1000L)));
    }

    private Runnable uncheck(RunnableEx runnableEx) {
        return () -> {
            try {
                runnableEx.run();
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
    }
}
