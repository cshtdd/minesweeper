package com.tddapps.minesweeper;

public class GameCell {
    private boolean mine;
    private int surroundingMinesCount;
    private boolean clicked;

    public GameCell(boolean isMine, int surroundingMinesCount, boolean clicked) {
        this.mine = isMine;
        this.surroundingMinesCount = surroundingMinesCount;
        this.clicked = clicked;
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

    public boolean clicked() {
        return clicked;
    }

    public void setClicked(boolean clicked) {
        this.clicked = clicked;
    }
}
