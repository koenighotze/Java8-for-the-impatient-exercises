package org.koenighotze.chapter2;

import java.util.*;
import java.util.stream.*;

import org.junit.*;

/**
 * Created by dschmitz on 10.02.15.
 */
public class Ex26Test {

    private Stream<Character> characterStream(String s) {
        List<Character> res = new ArrayList<>();
        for (char c : s.toCharArray()) res.add(c);
        return res.stream();
    }

    @Test
    public void testEx26() throws Exception {
        String str = "Hello World";
        characterStream(str).forEach(System.out::println);

        IntStream
                .range(0, str.length())
                .mapToObj(str::charAt)
                // why does java think that map results in ints unless I use mapToObj?
                //.map(str::charAt) -> because map is declared as returning an IntStream
                .forEach(c -> System.out.println(Character.toString(c)));
    }
}
