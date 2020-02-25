import com.tddapps.io.Reader;
import com.tddapps.io.Tty;
import com.tddapps.io.Writer;
import com.tddapps.minesweeper.Game;
import com.tddapps.minesweeper.GameDefault;

public class Program {
    private final Reader ioReader;
    private final Writer ioWriter;
    private final Game game;

    public static void main(String[] args){
        var io = new Tty();
        new Program(io, io, new GameDefault()).run();
    }

    Program(Reader ioReader, Writer ioWriter, Game game) {
        this.ioReader = ioReader;
        this.ioWriter = ioWriter;
        this.game = game;
    }

    void run(){
        display("Welcome to Minesweeper");

        if (!buildGame()){
            return;
        }

        display(game.toString());

        // TODO: finish this very tedious and annoying job
    }

    private boolean buildGame() {
        display("Select Game Size:");
        display("1- Easy: 10x10 15 mines");
        display("2- Medium: 20x20 35 mines");
        display("3- Hard: 50x50 503 mines");

        var selection = readInput();
        if (selection.startsWith("1")){
            game.generate(10, 10, 15);
        } else if (selection.startsWith("2")){
            game.generate(20, 20, 35);
        } else if (selection.startsWith("3")){
            game.generate(50, 50, 503);
        } else {
            ioWriter.display("Invalid Choice!");
            return false;
        }

        return true;
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
