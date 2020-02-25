package com.tddapps.io;

public class NumericMenuDefault implements NumericMenu {
    private final Reader reader;
    private final Writer writer;

    public NumericMenuDefault(Reader reader, Writer writer) {
        this.reader = reader;
        this.writer = writer;
    }

    @Override
    public int displayMenu(String title, String[] options) {
        writer.display(title);

        return 0;
    }
}
