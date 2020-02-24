package com.tddapps.minesweeper;

import java.util.Random;

class RandomNumberGeneratorImpl implements RandomNumberGenerator {
    private static final Random generator = new Random();

    @Override
    public int generate(int minInclusive, int maxExclusive) {
        int count = maxExclusive - minInclusive;
        return generator.nextInt(count) + minInclusive;
    }
}
