import com.tddapps.io.Reader;
import com.tddapps.minesweeper.Game;
import io.WriterStub;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class ProgramTest {
    private final WriterStub writer = new WriterStub();
    private final Reader reader = mock(Reader.class);
    private final Game game = mock(Game.class);
    private final Program program = new Program(reader, writer, game);

    @Test
    void RunWelcomesTheUser(){
        program.run();

        assertTrue(writer.contains("Welcome to minesweeper"));
    }

    @Test
    void InitializesAGame(){
        program.run();

        assertTrue(writer.contains("Select Game Size:"));
        assertTrue(writer.contains("1- Easy: 10x10 15 mines"));
        assertTrue(writer.contains("2- Medium: 20x20 35 mines"));
        assertTrue(writer.contains("3- Hard: 50x50 503 mines"));
    }
}
