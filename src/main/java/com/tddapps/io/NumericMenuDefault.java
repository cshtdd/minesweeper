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

        for (int i = 0; i < options.length; i++) {
            var menuItem = String.format("%d- %s", i + 1, options[i]);
            writer.display(menuItem);
        }

        return 0;
    }
}
