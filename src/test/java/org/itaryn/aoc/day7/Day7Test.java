package org.itaryn.aoc.day7;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Day7Test {
    @Test
    void countSplit() {
        Integer[][] splitters = new Integer[][]{
                new Integer[]{},
                new Integer[]{7},
                new Integer[]{},
                new Integer[]{6, 8},
                new Integer[]{},
                new Integer[]{5, 7, 9},
                new Integer[]{},
                new Integer[]{4, 6, 10},
                new Integer[]{},
                new Integer[]{3, 5, 9, 11},
                new Integer[]{},
                new Integer[]{2, 6, 12},
                new Integer[]{},
                new Integer[]{1, 3, 5, 7, 9, 13},
                new Integer[]{}
        };

        assertEquals(21, new Day7().countSplit(new TachyonManifold(7, splitters)));
    }

    @Test
    void countParticles() {
        Integer[][] splitters = new Integer[][]{
                new Integer[]{},
                new Integer[]{7},
                new Integer[]{},
                new Integer[]{6, 8},
                new Integer[]{},
                new Integer[]{5, 7, 9},
                new Integer[]{},
                new Integer[]{4, 6, 10},
                new Integer[]{},
                new Integer[]{3, 5, 9, 11},
                new Integer[]{},
                new Integer[]{2, 6, 12},
                new Integer[]{},
                new Integer[]{1, 3, 5, 7, 9, 13},
                new Integer[]{}
        };

        assertEquals(40, new Day7().countParticles(new TachyonManifold(7, splitters)));
    }

    @Test
    void readInput() throws IOException {
        Integer[][] expected = new Integer[][]{
                new Integer[]{},
                new Integer[]{7},
                new Integer[]{},
                new Integer[]{6, 8},
                new Integer[]{},
                new Integer[]{5, 7, 9},
                new Integer[]{},
                new Integer[]{4, 6, 10},
                new Integer[]{},
                new Integer[]{3, 5, 9, 11},
                new Integer[]{},
                new Integer[]{2, 6, 12},
                new Integer[]{},
                new Integer[]{1, 3, 5, 7, 9, 13},
                new Integer[]{}
        };
        TachyonManifold actual = new Day7().readInput("src/test/resources/day7/example.txt");

        assertEquals(7, actual.start());
        assertArrayEquals(expected, actual.splitters());
    }
}