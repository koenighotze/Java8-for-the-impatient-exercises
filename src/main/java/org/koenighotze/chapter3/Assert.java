package org.koenighotze.chapter3;

import java.util.function.*;

/**
 * Created by dschmitz on 24.03.15.
 */
public class Assert {

    public static void myAssert(Supplier<Boolean> cond, String message) {
        if (!cond.get()) {
            throw new AssertionError(message);
        }

    }
}
