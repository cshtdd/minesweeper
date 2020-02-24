package com.tddapps.minesweeper;

public class Game {
    private final int width;
    private final int height;
    private final int mines;
    private final RandomNumberGenerator randomNumberGenerator;
    private final GameCellFormatter cellFormatter;
    private final String[][] board;

    public Game(int width, int height, int mines) {
        this(width, height, mines,
                new RandomNumberGeneratorImpl());
    }

    Game(int width, int height, int mines, RandomNumberGenerator randomNumberGenerator) {
        this(width, height, mines, randomNumberGenerator,
                new GameCellFormatterAlwaysDisplayMine('M'));
    }

    Game(int width, int height, int mines,
         RandomNumberGenerator randomNumberGenerator,
         GameCellFormatter cellFormatter) {
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

        this.board = new String[height][width];
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
                var cell = new GameCell(containsMineAt(row, col), 0, false);
                result.append(cellFormatter.format(cell));
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
                board[row][col] = "M";
            }
        }
    }

    private boolean containsMineAt(int row, int col){
        return board[row][col] != null;
    }
}
