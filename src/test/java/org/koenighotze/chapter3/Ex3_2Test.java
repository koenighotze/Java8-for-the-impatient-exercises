package org.koenighotze.chapter3;

import org.junit.Ignore;
import org.junit.Test;

import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.koenighotze.chapter3.LockRunner.withLock;
/**
 * Created by dschmitz on 24.03.15.
 */
public class Ex3_2Test {

    @Test
    @Ignore("will fail due to race condition")
    public void testRepeat() throws Exception {
        IntStream.range(1, 100).forEach(i -> test());
    }

    public void test() {
        int[] sum = new int[] { 0 };

        // 5 * (5+1) / 2 = 15
        IntStream.rangeClosed(1, 5).parallel().forEach(i -> {
            sum[0] += i;
        });

        assertThat(sum[0], is(equalTo(15)));
    }



    @Test
    public void testLock() throws Exception {
        int[] sum = new int[] { 0 };

        ReentrantLock arrayLock = new ReentrantLock();
        // 5 * (5+1) / 2 = 15
        IntStream.rangeClosed(1, 5).parallel().forEach(i -> {
            withLock(arrayLock, () -> sum[0] += i);
        });

        assertThat(sum[0], is(equalTo(15)));

    }
}
