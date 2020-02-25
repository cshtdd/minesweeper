package com.tddapps.minesweeper;

class CellFormatterAlwaysDisplayMine implements CellFormatter {
    @Override
    public String format(Cell cell) {
        if (!cell.isMine()) {
            return "  ";
        }

        return "M ";
    }
}
