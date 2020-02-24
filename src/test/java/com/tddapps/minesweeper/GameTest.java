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
}
