package com.tddapps.minesweeper;

public class GameCell {
    private boolean isMine;
    private int surroundingMinesCount;
    private int clicked;

    public GameCell(boolean isMine, int surroundingMinesCount, int clicked) {
        this.isMine = isMine;
        this.surroundingMinesCount = surroundingMinesCount;
        this.clicked = clicked;
    }

    public boolean isMine() {
        return isMine;
    }

    public void setMine(boolean mine) {
        this.isMine = mine;
    }

    public int getSurroundingMinesCount() {
        return surroundingMinesCount;
    }

    public void setSurroundingMinesCount(int surroundingMinesCount) {
        this.surroundingMinesCount = surroundingMinesCount;
    }

    public int getClicked() {
        return clicked;
    }

    public void setClicked(int clicked) {
        this.clicked = clicked;
    }
}
