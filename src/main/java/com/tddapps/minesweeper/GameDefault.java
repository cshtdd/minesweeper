package com.tddapps.minesweeper;

public class GameDefault implements Game {
    private final RandomNumberGenerator randomNumberGenerator;
    private final CellFormatter cellFormatter;

    private int width;
    private int height;
    private int mines;
    private Cell[][] board;

    public GameDefault() {
        this(new RandomNumberGeneratorDefault(), new CellFormatterDefault());
    }

    GameDefault(RandomNumberGenerator randomNumberGenerator,
                CellFormatter cellFormatter) {
        this.randomNumberGenerator = randomNumberGenerator;
        this.cellFormatter = cellFormatter;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public int getMines() {
        return mines;
    }

    @Override
    public String toString() {
        var result = new StringBuilder();

        for (int row = 0; row < height; row++){
            for (int col = 0; col < width; col++){
                result.append(cellFormatter.format(board[row][col]));
            }
            result.append("\n");
        }

        return result.toString();
    }

    @Override
    public void flag(int row, int col) {
        validateLocationBoundaries(row, col);

        board[row][col].setFlagged(true);
    }

    @Override
    public void unflag(int row, int col) {
        validateLocationBoundaries(row, col);

        board[row][col].setFlagged(false);
    }

    @Override
    public void expand(int row, int col) {
        validateLocationBoundaries(row, col);

        board[row][col].setExpanded(true);
    }

    @Override
    public void generate(int height, int width, int mines) {
        validateDimensions(height, width, mines);

        this.width = width;
        this.height = height;
        this.mines = mines;
        this.board = new Cell[height][width];

        resetBoard();
        distributeMines();
        calculateSurroundingMineCounts();
    }

    private void resetBoard() {
        for (int row = 0; row < height; row++){
            for (int col = 0; col < width; col++){
                board[row][col] = new Cell();
            }
        }
    }

    private void distributeMines() {
        for (int i = 0; i < mines; i++) {
            int row = randomNumberGenerator.generate(0, height);
            int col = randomNumberGenerator.generate(0, width);

            if (containsMineAt(row, col)){
                i--;
            } else {
                board[row][col].setMine(true);
            }
        }
    }

    private void calculateSurroundingMineCounts() {
        for (int row = 0; row < height; row++){
            for (int col = 0; col < width; col++){
                var count = calculateSurroundingMineCount(row, col);
                board[row][col].setSurroundingMinesCount(count);
            }
        }
    }

    private int calculateSurroundingMineCount(int row, int col) {
        var result = 0;
        for (int y = row - 1; y <= row + 1; y++){
            for (int x = col - 1; x <= col + 1; x++) {
                if (y == row && x == col){
                    continue;
                }

                if (x < 0 || x >= width) {
                    continue;
                }

                if (y < 0 || y >= height) {
                    continue;
                }

                if (containsMineAt(y, x)){
                    result++;
                }
            }
        }
        return result;
    }

    private boolean containsMineAt(int row, int col){
        return board[row][col].isMine();
    }

    private void validateDimensions(int height, int width, int mines) {
        if (width < 1){
            throw new IllegalArgumentException("Width must be greater than zero");
        }

        if (height < 1){
            throw new IllegalArgumentException("Height must be greater than zero");
        }

        if (mines < 1){
            throw new IllegalArgumentException("Mines must be greater than zero");
        }

        if (mines > height * width){
            throw new IllegalArgumentException("Mines must be smaller than the number of squares");
        }
    }

    private void validateLocationBoundaries(int row, int col) {
        if (!isValidRow(row)){
            throw new IllegalArgumentException("Row out of bounds");
        }

        if (!isValidCol(col)){
            throw new IllegalArgumentException("Col out of bounds");
        }
    }

    @Override
    public boolean isOver() {
        var expandedCellCount = 0;

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                var cell = board[row][col];

                if (cell.isExpanded()) {
                    expandedCellCount++;
                }

                if (isIncorrectlyFlagged(cell, row, col)){
                    return false;
                }

                if (isMineExploded(cell, row, col)){
                    return true;
                }
            }
        }

        return allCellsAreExpanded(expandedCellCount);
    }

    private boolean isMineExploded(Cell cell, int row, int col) {
        return containsMineAt(row, col) && cell.isExpanded() && !cell.isFlagged();
    }

    private boolean isIncorrectlyFlagged(Cell cell, int row, int col) {
        return cell.isFlagged() && !containsMineAt(row, col);
    }

    private boolean allCellsAreExpanded(int count) {
        return count == width * height;
    }

    @Override
    public boolean isValidLocation(int row, int col) {
        return isValidRow(row) && isValidCol(col);
    }

    private boolean isValidCol(int col) {
        return col >= 0 && col < width;
    }

    private boolean isValidRow(int row) {
        return row >= 0 && row < height;
    }
}
