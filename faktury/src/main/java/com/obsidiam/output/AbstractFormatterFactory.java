package com.obsidiam.output;

import java.lang.reflect.InvocationTargetException;
import java.util.logging.Formatter;

/**
 * AbstractFormatterFactory, abstract class representing base for every other Formatter factory class.
 */
public abstract class AbstractFormatterFactory {
    protected abstract AbstractOutputFormatter getFormatter();
    protected abstract AbstractOutputFormatter getFormatter(FormatterType formatterType) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException;
}
