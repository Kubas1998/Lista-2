package com.obsidiam.output;


import java.util.logging.Formatter;

/**
 * AbstractOutputFormatter class, represents base class for every other formatter used to output data on the standard output.
 */
abstract class AbstractOutputFormatter extends Formatter {
    protected FormatterType formatterType;

    AbstractOutputFormatter(FormatterType formatterType){
        this.formatterType = formatterType;
    }
    public abstract FormatterType getFormatterType();
}
