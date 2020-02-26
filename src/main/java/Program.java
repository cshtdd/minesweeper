import com.tddapps.io.*;
import com.tddapps.minesweeper.Game;
import com.tddapps.minesweeper.GameDefault;

public class Program {
    public static final String[] GAME_OPTIONS = {
            "Expand a cell",
            "Flag a mine",
            "Remove flag"
    };
    private final Writer ioWriter;
    private final Game game;
    private final NumericMenu menu;

    public static void main(String[] args){
        var io = new Tty();
        var menu = new NumericMenuDefault(io, io);
        var game = new GameDefault();
        var program = new Program(io, game, menu);

        program.run();
    }

    Program(Writer ioWriter, Game game, NumericMenu menu) {
        this.ioWriter = ioWriter;
        this.game = game;
        this.menu = menu;
    }

    void run(){
        display("Welcome to Minesweeper");

        buildGame();

        while (!game.isOver()){
            display(game.toString());

            var task = displayGameMenu();
            switch (task.option){
                case 1:
                    game.expand(task.row, task.col);
                    break;
                case 2:
                    game.flag(task.row, task.col);
                    break;
                case 3:
                    game.unflag(task.row, task.col);
                    break;
            }
        }

        display(game.toString());
        display("Game Over");
    }

    private void buildGame() {
        var gameSizeOption = menu.displayMenu("Select Game Size:", new String[]{
                "Easy: 10x10 15 mines",
                "Medium: 20x20 35 mines",
                "Hard: 50x50 503 mines"
        });

        switch (gameSizeOption){
            case 1:
                game.generate(10, 10, 15);
                break;
            case 2:
                game.generate(20, 20, 35);
                break;
            case 3:
                game.generate(50, 50, 503);
                break;
        }
    }

    private void display(String str){
        ioWriter.display(str);
    }

    private static class GameSelection {
        public int option;
        public int row;
        public int col;
    }

    private GameSelection displayGameMenu(){
        var result = new GameSelection();
        result.option = menu.displayMenu("Select Task:", GAME_OPTIONS);

        result.row = menu.promptForNumber("Row:", 0, game.getHeight() - 1);
        result.col = menu.promptForNumber("Col:", 0, game.getWidth() - 1);

        return result;
    }
}
