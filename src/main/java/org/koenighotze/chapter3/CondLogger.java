package org.koenighotze.chapter3;

import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by dschmitz on 24.03.15.
 */
public class CondLogger {
    private Logger logger;

    public CondLogger(Level level) {
        this.logger = Logger.getLogger(CondLogger.class.getName());
        this.logger.setLevel(level);
    }

    public void logIf(Level level, Supplier<Boolean> cond, Supplier<String> consumer) {
        if (this.logger.isLoggable(level) && cond.get()) {
            this.logger.log(level, consumer);
        }
    }
}
