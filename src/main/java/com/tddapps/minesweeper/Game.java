package com.tddapps.minesweeper;

public class Game {
    private final int width;
    private final int height;
    private final int mines;

    public Game(int width, int height, int mines) {
        if (width < 1){
            throw new IllegalArgumentException("Width must be greater than zero");
        }

        if (height < 1){
            throw new IllegalArgumentException("Height must be greater than zero");
        }

        if (mines < 1){
            throw new IllegalArgumentException("Mines must be greater than zero");
        }

        if (mines > height * width){
            throw new IllegalArgumentException("Mines must be smaller than the number of squares");
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

    @Override
    public String toString() {
        var result = new StringBuilder();

        for (int row = 0; row < height; row++){
            for (int col = 0; col < width; col++){
                result.append("  ");
            }
            result.append("\n");
        }

        return result.toString();
    }
}
