package com.tddapps.minesweeper;

import java.util.Random;

class RandomNumberGeneratorImpl implements RandomNumberGenerator {
    private static final Random generator = new Random();

    @Override
    public int generate(int minInclusive, int maxInclusive) {
        int count = maxInclusive - minInclusive + 1;
        return generator.nextInt(count) + minInclusive;
    }
}
