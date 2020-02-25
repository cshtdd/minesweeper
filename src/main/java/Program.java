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
        ioWriter.display("Welcome to Minesweeper");
    }
}
