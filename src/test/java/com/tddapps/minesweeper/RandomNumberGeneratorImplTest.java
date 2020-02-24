package com.tddapps.minesweeper;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class RandomNumberGeneratorImplTest {
    public static final int MIN_INCLUSIVE = -100000;
    public static final int MAX_INCLUSIVE = 200000;
    private final RandomNumberGenerator generator = new RandomNumberGeneratorImpl();
    private final List<Integer> generatedNumbers = new ArrayList<Integer>();

    RandomNumberGeneratorImplTest(){
        for (int i = 0; i < 10000; i++) {
            generatedNumbers.add(generator.generate(MIN_INCLUSIVE, MAX_INCLUSIVE));
        }
    }

    @Test
    void GeneratesNumbersThatRarelyCollapse(){
        var uniqueNumbersCount = generatedNumbers.stream().distinct().count();
        assertTrue(uniqueNumbersCount >= (generatedNumbers.size() * 0.8));
    }

    @Test
    void GeneratesNumbersGreaterThanOrEqualThanTheMin(){
        var outOfBounds = generatedNumbers
                .stream()
                .filter(n -> n < MIN_INCLUSIVE)
                .toArray(Integer[]::new);
        assertArrayEquals(new Integer[0], outOfBounds);
    }

    @Test
    void GeneratesNumbersSmallerThanOrEqualThanTheMax(){
        var outOfBounds = generatedNumbers
                .stream()
                .filter(n -> n > MAX_INCLUSIVE)
                .toArray(Integer[]::new);
        assertArrayEquals(new Integer[0], outOfBounds);
    }
}
