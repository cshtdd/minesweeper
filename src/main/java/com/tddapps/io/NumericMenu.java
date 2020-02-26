package com.tddapps.io;

public interface NumericMenu {
    int displayMenu(String title, String[] options);
    int promptForNumber(String message, int min, int max);
}
