package com.tddapps.minesweeper;

public interface Game {
    void flag(int row, int col);

    void unflag(int row, int col);

    void expand(int row, int col);

    void generate(int height, int width, int mines);

    boolean isOver();

    boolean isValidLocation(int row, int col);
}
