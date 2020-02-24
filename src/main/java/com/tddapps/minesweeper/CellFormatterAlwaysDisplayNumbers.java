package com.tddapps.minesweeper;

class CellFormatterAlwaysDisplayNumbers implements CellFormatter {
    @Override
    public String format(Cell cell) {
        return String.format("%d ", cell.getSurroundingMinesCount());
    }
}
