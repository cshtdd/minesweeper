package com.tddapps.minesweeper;

class CellFormatterDisplayExpansions implements CellFormatter {
    @Override
    public String format(Cell cell) {
        if (cell.isExpanded()) {
            return "X ";
        }

        return "  ";
    }
}
