package com.tddapps.minesweeper;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GameTest {
    private final RandomNumberGenerator randomGeneratorMock = mock(RandomNumberGenerator.class);

    @Test
    void CanBeCreatedWithDimensionsAndMineCount(){
        var g = new Game(20, 10, 50);

        assertEquals(20, g.getWidth());
        assertEquals(10, g.getHeight());
        assertEquals(50, g.getMines());
    }

    @Test
    void CannotBeCreatedWhenWidthIsSmallerThanOne(){
        assertThrows(IllegalArgumentException.class, () -> new Game(-1, 10, 5));
        assertThrows(IllegalArgumentException.class, () -> new Game(0, 10, 5));
    }

    @Test
    void CannotBeCreatedWhenHeightIsSmallerThanOne(){
        assertThrows(IllegalArgumentException.class, () -> new Game(20, 0, 5));
        assertThrows(IllegalArgumentException.class, () -> new Game(20, -1, 5));
    }

    @Test
    void CannotBeCreatedWhenTheNumberOfMinesIsSmallerThanOne(){
        assertThrows(IllegalArgumentException.class, () -> new Game(20, 10, -1));
        assertThrows(IllegalArgumentException.class, () -> new Game(20, 10, 0));
    }

    @Test
    void CannotBeCreatedWhenTheNumberOfMinesIsGreaterThanTheNumberOfSquares(){
        assertThrows(IllegalArgumentException.class, () -> new Game(1, 1, 2));
        assertThrows(IllegalArgumentException.class, () -> new Game(20, 10, 201));
    }

    @Test
    void ProducesAStringRepresentationOfAnEmptyBoard(){
        var g = new Game(3, 4, 2);
        var expected =
                "      \n" +
                "      \n" +
                "      \n" +
                "      \n";
        var actual = g.toString();

        assertEquals(expected, actual);
    }

    @Test
    void AssignsMines(){
        // these are the positions of the mines
        // the game should be two calls to the random number generator for each mine
        // if the mine position already contains a mine
        //  two additional calls to the random number generator will be made
        when(randomGeneratorMock.generate(0, 3)).thenReturn(2, 2, 0, 0, 1, 1, 0); //rows
        when(randomGeneratorMock.generate(0, 4)).thenReturn(0, 1, 1, 1, 2, 2, 3); //cols
        var g = new Game(4, 3, 5, randomGeneratorMock,
                new CellFormatterAlwaysDisplayMine());
        g.reset();

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

        var g = new Game(4, 3, 3, randomGeneratorMock,
                new CellFormatterAlwaysDisplayNumbers());
        g.reset();

        var expected =
                "0 0 0 0 \n" +
                "1 0 0 0 \n" +
                "0 2 1 0 \n";
        assertEquals(expected, g.toString());
    }
}
