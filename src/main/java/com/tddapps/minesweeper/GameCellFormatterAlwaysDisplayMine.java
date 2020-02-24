package com.tddapps.minesweeper;

class GameCellFormatterAlwaysDisplayMine implements GameCellFormatter {
    private final char mineChar;

    GameCellFormatterAlwaysDisplayMine(){
        this('M');
    }

    GameCellFormatterAlwaysDisplayMine(char mineChar){
        this.mineChar = mineChar;
    }

    @Override
    public String format(GameCell cell) {
        if (cell.isMine()){
            return String.format("%s ", mineChar);
        } else {
            return "  ";
        }
    }
}
