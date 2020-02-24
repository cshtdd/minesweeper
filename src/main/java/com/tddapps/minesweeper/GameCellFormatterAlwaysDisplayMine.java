package com.tddapps.minesweeper;

public class GameCellFormatterAlwaysDisplayMine implements GameCellFormatter {
    @Override
    public String format(GameCell cell) {
        if (cell.isMine()){
            return "x ";
        } else {
            return "  ";
        }
    }
}
