package com.tddapps.io;

public class NumericMenuDefault implements NumericMenu {
    public static final int INVALID_CHOICE = -1;
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
            writer.display(String.format("%d- %s", i + 1, options[i]));
        }

        return readNumericInputWithinBoundaries(1, options.length);
    }

    @Override
    public int promptForNumber(String message, int min, int max) {
        writer.display(String.format("%s [%d-%d]", message, min, max));

        return readNumericInputWithinBoundaries(min, max);
    }

    private void displayInvalidInputMessage(int min, int max) {
        writer.display("Invalid Input!");
        writer.display(String.format("Please enter a number between %d and %d", min, max));
    }

    private int parseOption(String str){
        return parseOption(str, INVALID_CHOICE);
    }

    private int readNumericInputWithinBoundaries(int min, int max) {
        while(true){
            var rawInput = reader.read();
            var value = parseOption(rawInput, min - 1);

            if (value >= min && value <= max){
                return value;
            }

            displayInvalidInputMessage(min, max);
        }
    }

    private int parseOption(String str, int defaultValue){
        try {
            return Integer.parseInt(str);
        }
        catch (NumberFormatException e){
            return defaultValue;
        }
    }
}
