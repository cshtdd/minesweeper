package com.tddapps.minesweeper;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GameTest {
    private final RandomNumberGenerator randomGeneratorMock = mock(RandomNumberGenerator.class);

    @Test
    void CanBeCreatedWithDimensionsAndMineCount(){
        var g = new Game();

        g.generate(10, 20, 50);

        assertEquals(20, g.getWidth());
        assertEquals(10, g.getHeight());
        assertEquals(50, g.getMines());
    }

    @Test
    void CannotBeCreatedWhenWidthIsSmallerThanOne(){
        var g = new Game();

        assertThrows(IllegalArgumentException.class, () -> g.generate(10, -1, 5));
        assertThrows(IllegalArgumentException.class, () -> g.generate(10, 0, 5));
    }

    @Test
    void CannotBeCreatedWhenHeightIsSmallerThanOne(){
        var g = new Game();

        assertThrows(IllegalArgumentException.class, () -> g.generate(0, 20, 5));
        assertThrows(IllegalArgumentException.class, () -> g.generate(-1, 20, 5));
    }

    @Test
    void CannotBeCreatedWhenTheNumberOfMinesIsSmallerThanOne(){
        var g = new Game();

        assertThrows(IllegalArgumentException.class, () -> g.generate(10, 20, -1));
        assertThrows(IllegalArgumentException.class, () -> g.generate(10, 20, 0));
    }

    @Test
    void CannotBeCreatedWhenTheNumberOfMinesIsGreaterThanTheNumberOfSquares(){
        var g = new Game();

        assertThrows(IllegalArgumentException.class, () -> g.generate(1, 1, 2));
        assertThrows(IllegalArgumentException.class, () -> g.generate(10, 20, 201));
    }

    @Test
    void ProducesAStringRepresentationOfANewGame(){
        assertEquals("", new Game().toString());
    }

    @Test
    void ProducesAStringRepresentationOfAnEmptyBoard(){
        var g = new Game();
        g.generate(4, 3, 2);
        var expected =
                "      \n" +
                "      \n" +
                "      \n" +
                "      \n";

        assertEquals(expected, g.toString());
    }

    @Test
    void AssignsMines(){
        // these are the positions of the mines
        // the game should be two calls to the random number generator for each mine
        // if the mine position already contains a mine
        //  two additional calls to the random number generator will be made
        when(randomGeneratorMock.generate(0, 3)).thenReturn(2, 2, 0, 0, 1, 1, 0); //rows
        when(randomGeneratorMock.generate(0, 4)).thenReturn(0, 1, 1, 1, 2, 2, 3); //cols
        var g = new Game(randomGeneratorMock,
                new CellFormatterAlwaysDisplayMine());
        g.generate(3, 4, 5);

        var expected =
                "  M   M \n" +
                "    M   \n" +
                "M M     \n";
        assertEquals(expected, g.toString());
    }

    @Test
    void CalculatesTheNumberOfSurroundingMinesForEachPosition(){
        when(randomGeneratorMock.generate(0, 3)).thenReturn(1, 2, 2); //rows
        when(randomGeneratorMock.generate(0, 4)).thenReturn(0, 1, 2); //cols

        var g = new Game(randomGeneratorMock,
                new CellFormatterAlwaysDisplayNumbers());
        g.generate(3, 4, 3);

        var expected =
                "1 1 0 0 \n" +
                "1 3 2 1 \n" +
                "2 2 1 1 \n";
        assertEquals(expected, g.toString());
    }

    @Test
    void FlagsMines(){
        var g = new Game(new RandomNumberGeneratorDefault(),
                new CellFormatterDisplayFlags());
        g.generate(3, 3, 1);

        g.flag(0, 2);
        g.flag(1, 1);
        g.flag(2, 0);

        var expected =
                "    F \n" +
                "  F   \n" +
                "F     \n";
        assertEquals(expected, g.toString());
    }

    @Test
    void CannotFlagLocationsOutsideOfTheBounds(){
        var g = new Game(new RandomNumberGeneratorDefault(),
                new CellFormatterDisplayFlags());
        g.generate(3, 4, 1);

        assertThrows(IllegalArgumentException.class, () -> g.flag(-1, 0));
        assertThrows(IllegalArgumentException.class, () -> g.flag(3, 0));

        assertThrows(IllegalArgumentException.class, () -> g.flag(0, -1));
        assertThrows(IllegalArgumentException.class, () -> g.flag(0, 4));
    }

    @Test
    void UnflagMines(){
        var g = new Game(new RandomNumberGeneratorDefault(),
                new CellFormatterDisplayFlags());
        g.generate(3, 3, 1);

        g.flag(0, 2);
        g.flag(1, 1);
        g.flag(2, 0);

        g.unflag(0, 2);
        g.unflag(2, 0);

        var expected =
                "      \n" +
                        "  F   \n" +
                        "      \n";
        assertEquals(expected, g.toString());
    }

    @Test
    void CannotUnflagLocationsOutsideOfTheBounds(){
        var g = new Game(new RandomNumberGeneratorDefault(),
                new CellFormatterDisplayFlags());
        g.generate(3, 4, 1);

        assertThrows(IllegalArgumentException.class, () -> g.unflag(-1, 0));
        assertThrows(IllegalArgumentException.class, () -> g.unflag(3, 0));

        assertThrows(IllegalArgumentException.class, () -> g.unflag(0, -1));
        assertThrows(IllegalArgumentException.class, () -> g.unflag(0, 4));
    }

    @Test
    void IsValidLocationChecksBoundaries(){
        var g = new Game();
        g.generate(10, 20, 5);

        assertFalse(g.isValidLocation(-1, 0));
        assertFalse(g.isValidLocation(10, 0));

        assertFalse(g.isValidLocation(0, -1));
        assertFalse(g.isValidLocation(0, 20));

        for (int row = 0; row < g.getHeight(); row++) {
            for (int col = 0; col < g.getWidth(); col++) {
                assertTrue(g.isValidLocation(row, col));
            }
        }
    }

    @Test
    void ExpandsCells(){
        var g = new Game(new RandomNumberGeneratorDefault(),
                new CellFormatterDisplayExpansions());
        g.generate(3, 3, 1);

        g.expand(0, 2);
        g.expand(1, 1);
        g.expand(2, 0);

        var expected =
                "    X \n" +
                "  X   \n" +
                "X     \n";
        assertEquals(expected, g.toString());
    }

    @Test
    void CannotExpandLocationsOutsideOfTheBounds(){
        var g = new Game(new RandomNumberGeneratorDefault(),
                new CellFormatterDisplayFlags());
        g.generate(3, 4, 1);

        assertThrows(IllegalArgumentException.class, () -> g.expand(-1, 0));
        assertThrows(IllegalArgumentException.class, () -> g.expand(3, 0));

        assertThrows(IllegalArgumentException.class, () -> g.expand(0, -1));
        assertThrows(IllegalArgumentException.class, () -> g.expand(0, 4));
    }

    @Test
    void GameIsOverByDefault(){
        var g = new Game();

        assertTrue(g.isOver());
    }

    @Test
    void GameIsNotOverAfterGeneration(){
        var g = new Game();

        g.generate(20, 10, 20);
        assertFalse(g.isOver());
    }

    @Test
    void GameIsOverWhenExpandingAMine(){
        when(randomGeneratorMock.generate(0, 3)).thenReturn(0, 2); //rows
        when(randomGeneratorMock.generate(0, 4)).thenReturn(0, 2); //cols

        var g = new Game(randomGeneratorMock, new CellFormatterDefault());
        g.generate(3, 4, 2);

        g.expand(2, 2);

        assertTrue(g.isOver());
    }

    @Test
    void GameIsNotOverWhenExpandingAFlaggedMine(){
        when(randomGeneratorMock.generate(0, 3)).thenReturn(0, 2); //rows
        when(randomGeneratorMock.generate(0, 4)).thenReturn(0, 2); //cols

        var g = new Game(randomGeneratorMock, new CellFormatterDefault());
        g.generate(3, 4, 2);

        g.flag(2, 2);
        g.expand(2, 2);

        assertFalse(g.isOver());
    }

    @Test
    void GameIsOverWhenAllCellsHaveBeenExpandedAndAllMinesHaveBeenFlagged(){
        when(randomGeneratorMock.generate(0, 3)).thenReturn(0, 2); //rows
        when(randomGeneratorMock.generate(0, 4)).thenReturn(0, 2); //cols

        var g = new Game(randomGeneratorMock, new CellFormatterDefault());
        g.generate(3, 4, 2);

        g.flag(0, 0);
        g.flag(2, 2);

        for (int row = 0; row < g.getHeight(); row++) {
            for (int col = 0; col < g.getWidth(); col++) {
                g.expand(row, col);
            }
        }

        assertTrue(g.isOver());
    }

    @Test
    void GameIsNotOverFlaggingANonMine(){
        when(randomGeneratorMock.generate(0, 3)).thenReturn(0, 2); //rows
        when(randomGeneratorMock.generate(0, 4)).thenReturn(0, 2); //cols

        var g = new Game(randomGeneratorMock, new CellFormatterDefault());
        g.generate(3, 4, 2);

        g.flag(0, 0);
        g.flag(2, 2);

        for (int row = 0; row < g.getHeight(); row++) {
            for (int col = 0; col < g.getWidth(); col++) {
                g.expand(row, col);
            }
        }


        g.flag(1, 1);


        assertFalse(g.isOver());
    }
}
