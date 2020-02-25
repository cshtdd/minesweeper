import com.tddapps.io.*;
import com.tddapps.minesweeper.Game;
import com.tddapps.minesweeper.GameDefault;

public class Program {
    private final Reader ioReader;
    private final Writer ioWriter;
    private final Game game;
    private final NumericMenu menu;

    public static void main(String[] args){
        var io = new Tty();
        new Program(io, io, new GameDefault(), new NumericMenuDefault(io, io)).run();
    }

    Program(Reader ioReader, Writer ioWriter, Game game, NumericMenu menu) {
        this.ioReader = ioReader;
        this.ioWriter = ioWriter;
        this.game = game;
        this.menu = menu;
    }

    void run(){
        display("Welcome to Minesweeper");

        buildGame();
        display(game.toString());

        // TODO: finish this very tedious and annoying job
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

    private String readInput() {
        var result = ioReader.read();
        if (result == null){
            return "";
        }

        return result;
    }
}
