package com.obsidiam.util;

import java.util.logging.Handler;
import java.util.logging.LogRecord;

/**
 * Handler for basing handling data written to the standard output buffer
 * @see Handler
 */
public class BasicHandler extends Handler {
    @Override
    public void publish(LogRecord record) {
        System.out.println(this.getFormatter().format(record));
    }

    @Override
    public void flush() {
        //Nothing to be done here.
    }

    @Override
    public void close() throws SecurityException {
        //Nothing to be done here.
    }
}
