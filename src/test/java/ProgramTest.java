import com.tddapps.io.NumericMenu;
import com.tddapps.io.Writer;
import com.tddapps.minesweeper.Game;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class ProgramTest {
    private final Writer writer = mock(Writer.class);
    private final Game game = mock(Game.class);
    private final NumericMenu menu = mock(NumericMenu.class);
    private final Program program = new Program(writer, game, menu);

    @Test
    void RunWelcomesTheUser(){
        when(game.isOver()).thenReturn(true);

        program.run();

        verify(writer).display("Welcome to Minesweeper");
    }

    @Test
    void CreatesAGameBasedOnTheInput(){
        when(game.isOver()).thenReturn(true);
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
        when(game.isOver()).thenReturn(true);
        when(game.toString()).thenReturn("GAME STATUS");
        when(menu.displayMenu(anyString(), any(String[].class))).thenReturn(2);

        program.run();

        verify(writer).display("GAME STATUS");
    }

    @Test
    void PerformsMultipleActivitiesUntilTheGameEnds(){
        when(menu.displayMenu("Select Game Size:", new String[]{
                "Easy: 10x10 15 mines",
                "Medium: 20x20 35 mines",
                "Hard: 50x50 503 mines"
        })).thenReturn(1);

        when(menu.displayMenu("Select Task:", new String[]{
                "Expand a cell",
                "Flag a mine",
                "Remove flag"
        })).thenReturn(1, 1, 2, 3, 1);

        when(game.getHeight()).thenReturn(10);
        when(game.getWidth()).thenReturn(10);
        when(game.isOver()).thenReturn(false, false, false, false, false, true);

        when(menu.promptForNumber("Row:", 0, 9)).thenReturn(0, 1, 5, 5, 9);
        when(menu.promptForNumber("Col:", 0, 9)).thenReturn(1, 2, 6, 6, 8);


        program.run();


        verify(game).generate(10, 10, 15);
        verify(game).expand(0, 1);
        verify(game).expand(1, 2);
        verify(game).flag(5, 6);
        verify(game).unflag(5, 6);
        verify(game).expand(9, 8);

        verify(writer).display("Game Over");
    }
}
