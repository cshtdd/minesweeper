package com.tddapps.minesweeper;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CellFormatterDefaultTest {
    private final CellFormatter formatter = new CellFormatterDefault();

    @Test
    void DisplaysAnEmptyIndicatorStringByDefault(){
        var cell = new Cell();

        assertEquals("_ ", formatter.format(cell));
    }

    @Test
    void DisplaysAFlagWhenTheCellHasBeenFlagged(){
        var cell = new Cell();
        cell.setFlagged(true);

        assertEquals("F ", formatter.format(cell));
    }

    @Test
    void DisplaysTheNumberOfSurroundingMinesWhenExpanded(){
        var cell = new Cell();
        cell.setExpanded(true);
        cell.setSurroundingMinesCount(5);

        assertEquals("5 ", formatter.format(cell));
    }

    @Test
    void DisplaysEmptyWhenExpandedButThereAreNoSurroundingMines(){
        var cell = new Cell();
        cell.setExpanded(true);

        assertEquals("  ", formatter.format(cell));
    }

    @Test
    void DisplayExplosionWhenExpandedButCellHasMine(){
        var cell = new Cell();
        cell.setExpanded(true);
        cell.setMine(true);

        assertEquals("X ", formatter.format(cell));
    }
}
