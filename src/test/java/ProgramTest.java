import com.tddapps.io.NumericMenu;
import com.tddapps.io.Reader;
import com.tddapps.minesweeper.Game;
import io.WriterStub;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProgramTest {
    private final WriterStub writer = new WriterStub();
    private final Reader reader = mock(Reader.class);
    private final Game game = mock(Game.class);
    private final NumericMenu menu = mock(NumericMenu.class);
    private final Program program = new Program(reader, writer, game, menu);

    ProgramTest(){
        when(game.toString()).thenReturn("GAME STATUS");
    }

    @Test
    void RunWelcomesTheUser(){
        program.run();

        assertTrue(writer.contains("Welcome to minesweeper"));
    }

    @Test
    void CreatesAGameBasedOnTheInput(){
        when(menu.displayMenu("Select Game Size:", new String[]{
                "Easy: 10x10 15 mines",
                "Medium: 20x20 35 mines",
                "Hard: 50x50 503 mines"
        })).thenReturn(3);

        program.run();

        verify(game).generate(50, 50, 503);
    }

    @Test
    void RendersTheGame(){
        when(menu.displayMenu(anyString(), any(String[].class))).thenReturn(2);

        program.run();

        assertTrue(writer.contains("game status"));
    }
}
