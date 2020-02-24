package com.tddapps.minesweeper;

class Cell {
    private boolean mine;
    private int surroundingMinesCount;

    public Cell(){
        this(false, 0);
    }

    public Cell(boolean isMine, int surroundingMinesCount) {
        this.mine = isMine;
        this.surroundingMinesCount = surroundingMinesCount;
    }

    public boolean isMine() {
        return mine;
    }

    public void setMine(boolean mine) {
        this.mine = mine;
    }

    public int getSurroundingMinesCount() {
        return surroundingMinesCount;
    }

    public void setSurroundingMinesCount(int surroundingMinesCount) {
        this.surroundingMinesCount = surroundingMinesCount;
    }
}
