package org.koenighotze.chapter3;

import java.util.concurrent.locks.*;

/**
 * Created by dschmitz on 24.03.15.
 */
public class LockRunner {

    public static void withLock(ReentrantLock lock, Runnable runnable) {
        lock.lock();

        try {
            runnable.run();
        }
        finally {
            lock.unlock();
        }
    }
}
