package com.tddapps.minesweeper;

class GameCellFormatterAlwaysDisplayNumbers implements GameCellFormatter {
    @Override
    public String format(GameCell cell) {
        return String.format("%d ", cell.getSurroundingMinesCount());
    }
}
