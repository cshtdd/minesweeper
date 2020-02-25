package com.tddapps.io;

public class Tty implements Writer, Reader {
    @Override
    public void display(String str) {
        System.out.println(str);
    }

    @Override
    public String read() {
        return new java.util.Scanner(System.in).next();
    }
}
