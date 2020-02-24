package com.tddapps.minesweeper;

public class Game {
    private final int width;
    private final int height;
    private final int mines;

    public Game(int width, int height, int mines) {
        if (width < 1){
            throw new IllegalArgumentException("Width must be greater than zero");
        }

        this.width = width;
        this.height = height;
        this.mines = mines;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getMines() {
        return mines;
    }
}
