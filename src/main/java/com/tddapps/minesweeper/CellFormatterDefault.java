package com.tddapps.minesweeper;

class CellFormatterDefault implements CellFormatter {
    @Override
    public String format(Cell cell) {
        if (cell.isFlagged()){
            return "F ";
        }

        if (cell.isExpanded()){
            if (cell.isMine()){
                return "X ";
            }

            int count = cell.getSurroundingMinesCount();

            if (count == 0){
                return "  ";
            }

            return String.format("%d ", count);
        }

        return "_ ";
    }
}
