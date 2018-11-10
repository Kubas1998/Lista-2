package com.obsidiam.output;

import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

/**
 * Implementation of AbstractFormatterFactory class
 * @see AbstractFormatterFactory
 */
public class StandardFormatterFactory extends AbstractFormatterFactory{
    private volatile static StandardFormatterFactory ourInstance = new StandardFormatterFactory();

    private StandardFormatterFactory() { }

    public synchronized static StandardFormatterFactory getInstance() {
        return ourInstance;
    }

    @Override
    public AbstractOutputFormatter getFormatter() {
        return new StandardOutputFormatter(FormatterType.BASIC);
    }

    @Override
    public AbstractOutputFormatter getFormatter(FormatterType formatterType) {
        Class<?> formatterClassRep = null;
        AbstractOutputFormatter returnFormatter = null;
        try {
            formatterClassRep = ClassLoader.getSystemClassLoader().loadClass(this.getClass().getPackage().getName()+"."+formatterType.getFormatterName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            returnFormatter = (AbstractOutputFormatter) Objects.requireNonNull(formatterClassRep).getConstructor(FormatterType.class).newInstance(formatterType);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return returnFormatter;
    }
}
