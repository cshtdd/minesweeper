package com.tddapps.minesweeper;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class RandomNumberGeneratorDefaultTest {
    public static final int MIN_INCLUSIVE = -100000;
    public static final int MAX_EXCLUSIVE = 200000;
    private final RandomNumberGenerator generator = new RandomNumberGeneratorDefault();
    private final List<Integer> generatedNumbers = new ArrayList<>();

    RandomNumberGeneratorDefaultTest(){
        for (int i = 0; i < 10000; i++) {
            generatedNumbers.add(generator.generate(MIN_INCLUSIVE, MAX_EXCLUSIVE));
        }
    }

    @Test
    void CannotGenerateNumbersWhenTheBoundariesAreInvalid(){
        assertThrows(IllegalArgumentException.class, () -> generator.generate(0, 0));
        assertThrows(IllegalArgumentException.class, () -> generator.generate(10, 10));
        assertThrows(IllegalArgumentException.class, () -> generator.generate(1, 0));
        assertThrows(IllegalArgumentException.class, () -> generator.generate(-10, -20));
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
    void GeneratesNumbersSmallerThanThanTheMax(){
        var outOfBounds = generatedNumbers
                .stream()
                .filter(n -> n >= MAX_EXCLUSIVE)
                .toArray(Integer[]::new);
        assertArrayEquals(new Integer[0], outOfBounds);
    }
}
