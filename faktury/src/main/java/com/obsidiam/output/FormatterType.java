package com.obsidiam.output;

/**
 * Represents formatter type.
 */
public enum FormatterType {
    BASIC("StandardOutputFormatter"),
    SUMMARY("SummaryOutputFormatter");

    private final String formatterName;

    FormatterType(String formatterName){
        this.formatterName = formatterName;
    }

    public String getFormatterName() {
        return formatterName;
    }
}
