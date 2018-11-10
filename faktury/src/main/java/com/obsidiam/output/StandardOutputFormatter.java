package com.obsidiam.output;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.LogRecord;

/**
 * Implementation of AbstractOutputFormatter
 * @see AbstractOutputFormatter
 */
public class StandardOutputFormatter extends AbstractOutputFormatter {
    StandardOutputFormatter(FormatterType formatterType) {
        super(formatterType);
        this.formatterType = formatterType;
    }

    @Override
    public String format(LogRecord record) {
        return new SimpleDateFormat("dd-MM-YY HH:mm:ss").format(new Date(record.getMillis()))+" : "+record.getLevel().getLocalizedName()+" : "+record.getMessage();
    }

    @Override
    public FormatterType getFormatterType() {
        return this.formatterType;
    }
}
