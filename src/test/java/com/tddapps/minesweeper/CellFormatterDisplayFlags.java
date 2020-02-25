package com.tddapps.minesweeper;

class CellFormatterDisplayFlags implements CellFormatter {
    @Override
    public String format(Cell cell) {
        if (cell.isFlagged()) {
            return "F ";
        }

        return "  ";
    }
}
