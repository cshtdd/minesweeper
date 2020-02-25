package com.tddapps.io;

public class Tty implements Printer {
    @Override
    public void display(String str) {
        System.out.println(str);
    }
}
