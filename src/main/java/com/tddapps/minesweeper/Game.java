package com.tddapps.minesweeper;

public class Game {
    private final int width;
    private final int height;
    private final int mines;
    private final RandomNumberGenerator randomNumberGenerator;
    private final CellFormatter cellFormatter;
    private final Cell[][] board;

    public Game(int width, int height, int mines) {
        this(width, height, mines, new RandomNumberGeneratorDefault(), new CellFormatterDefault());
    }

    Game(int width, int height, int mines,
         RandomNumberGenerator randomNumberGenerator,
         CellFormatter cellFormatter) {
        this.randomNumberGenerator = randomNumberGenerator;
        this.cellFormatter = cellFormatter;
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

        this.width = width;
        this.height = height;
        this.mines = mines;

        this.board = new Cell[height][width];
        for (int row = 0; row < height; row++){
            for (int col = 0; col < width; col++){
                board[row][col] = new Cell();
            }
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

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

    public void initialize() {
        for (int i = 0; i < mines; i++) {
            int row = randomNumberGenerator.generate(0, height);
            int col = randomNumberGenerator.generate(0, width);

            if (containsMineAt(row, col)){
                i--;
            } else {
                board[row][col].setMine(true);
            }
        }

        for (int row = 0; row < height; row++){
            for (int col = 0; col < width; col++){
                if (containsMineAt(row, col)){
                    var count = calculateMineCountSurrounding(row, col);
                    board[row][col].setSurroundingMinesCount(count);
                }
            }
        }
    }

    private int calculateMineCountSurrounding(int row, int col) {
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
}
