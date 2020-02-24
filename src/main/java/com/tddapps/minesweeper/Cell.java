package com.tddapps.minesweeper;

class Cell {
    private boolean mine;
    private int surroundingMinesCount;
    private boolean flagged;
    private boolean expanded;

    public Cell(){
        this(false, 0, false, false);
    }

    public Cell(boolean isMine, int surroundingMinesCount, boolean expanded, boolean flagged) {
        this.mine = isMine;
        this.surroundingMinesCount = surroundingMinesCount;
        this.expanded = expanded;
        this.flagged = flagged;
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

    public boolean isFlagged() {
        return flagged;
    }

    public void setFlagged(boolean flagged) {
        this.flagged = flagged;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }
}
