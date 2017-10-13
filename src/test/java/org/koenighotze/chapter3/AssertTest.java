package org.koenighotze.chapter3;

import static org.koenighotze.chapter3.Assert.myAssert;

import org.junit.*;

public class AssertTest {
    @Test
    public void test() throws Exception {
        myAssert(() -> 1 == 1, "1 should be 1");
    }

    @Test(expected = AssertionError.class)
    public void testFailure() throws Exception {
        myAssert(() -> 0 == 1, "0 should not be 1");
    }
}
