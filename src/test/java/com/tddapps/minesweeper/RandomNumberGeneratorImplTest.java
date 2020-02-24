package com.tddapps.minesweeper;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class RandomNumberGeneratorImplTest {
    @Test
    void GeneratesNumbersWithinARangeThatRarelyCollapse(){
        var g = new RandomNumberGeneratorImpl();
        var l = new ArrayList<Integer>();

        for (int i = 0; i < 10000; i++) {
            l.add(g.generate(-100, 100));
        }

        var uniqueNumbersCount = l.stream().distinct().count();
        assertTrue(uniqueNumbersCount >= (l.size() * 0.8));
    }
}
