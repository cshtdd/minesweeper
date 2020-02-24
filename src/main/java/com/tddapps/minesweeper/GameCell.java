package com.tddapps.minesweeper;

public class GameCell {
    private boolean hasMine;
    private int surroundingMinesCount;
    private int clicked;

    public GameCell(boolean hasMine, int surroundingMinesCount, int clicked) {
        this.hasMine = hasMine;
        this.surroundingMinesCount = surroundingMinesCount;
        this.clicked = clicked;
    }

    public boolean isHasMine() {
        return hasMine;
    }

    public void setHasMine(boolean hasMine) {
        this.hasMine = hasMine;
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
