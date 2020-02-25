package com.tddapps.minesweeper;

class CellFormatterAlwaysDisplayMine implements CellFormatter {
    private final char mineChar;

    CellFormatterAlwaysDisplayMine(){
        this('M');
    }

    CellFormatterAlwaysDisplayMine(char mineChar){
        this.mineChar = mineChar;
    }

    @Override
    public String format(Cell cell) {
        if (cell.isMine()){
            return String.format("%s ", mineChar);
        } else {
            return "  ";
        }
    }
}
