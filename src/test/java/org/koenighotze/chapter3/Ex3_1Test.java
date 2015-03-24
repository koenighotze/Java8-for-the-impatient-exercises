package org.koenighotze.chapter3;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.atomic.LongAdder;
import java.util.function.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.IntStream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by dschmitz on 14.03.15.
 */
public class Ex3_1Test {
//    private Logger logger = Logger.getLogger(Ex3_1Test.class.getName());
    private LongAdder numEvals;

    private CondLogger logger;

    @Before
    public void setup() {
        this.logger = new CondLogger(Level.INFO);
        this.numEvals = new LongAdder();
    }




    @Test
    public void testLogIf() throws Exception {
        int[] a = new int[] { 1, 2, 3, 4 , 5 , 6, 7, 8, 9, 10 };

        IntStream
            .rangeClosed(0, 9)
            .forEach(
                i -> this.logger.logIf(Level.SEVERE, () -> {
                        Ex3_1Test.this.numEvals.increment();
                        return i == 9;
                    }, () -> "a[9] == " + a[i]));

        assertThat(this.numEvals.longValue(), is(equalTo(10L)));
    }


    @Test
    public void testLogIfNoEval() throws Exception {
        int[] a = new int[] { 1, 2, 3, 4 , 5 , 6, 7, 8, 9, 10 };

        IntStream
            .rangeClosed(0, 9)
            .forEach(
                i -> this.logger.logIf(Level.FINER, () -> {
                        Ex3_1Test.this.numEvals.increment();
                        return i == 9;
                    }, () -> "a[9] == " + a[i]));

        assertThat(this.numEvals.longValue(), is(equalTo(0L)));
    }

    @Test
    public void testEnsureCondIsNotCalledIfNotLoggable() throws Exception {
        int[] a = new int[] { 1, 2, 3, 4 , 5 , 6, 7, 8, 9, 10 };

        IntStream
            .rangeClosed(0, 9)
            .forEach(
                i -> this.logger.logIf(Level.FINE, // Logger is set to INFO
                    () -> {
                        Ex3_1Test.this.numEvals.increment();
                        return i == 9;
                    },
                    () -> { throw new RuntimeException("BUMM"); }));

        assertThat(this.numEvals.longValue(), is(equalTo(0L)));
    }
}
