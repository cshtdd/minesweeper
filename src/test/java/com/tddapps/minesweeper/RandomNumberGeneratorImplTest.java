package com.tddapps.minesweeper;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class RandomNumberGeneratorImplTest {
    private final RandomNumberGenerator generator = new RandomNumberGeneratorImpl();
    private final List<Integer> generatedNumbers = new ArrayList<Integer>();

    RandomNumberGeneratorImplTest(){
        for (int i = 0; i < 10000; i++) {
            generatedNumbers.add(generator.generate(-100, 100));
        }
    }

    @Test
    void GeneratesNumbersThatRarelyCollapse(){
        var uniqueNumbersCount = generatedNumbers.stream().distinct().count();
        assertTrue(uniqueNumbersCount >= (generatedNumbers.size() * 0.8));
    }
}
