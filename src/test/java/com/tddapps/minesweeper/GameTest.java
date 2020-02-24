package com.tddapps.minesweeper;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {
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
}
