package org.itaryn.aoc.day5;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Day5Test {
    @Test
    void getSecondStar() throws IOException {
        String actual = new Day5().getSecondStar("src/test/resources/day5/example.txt");
        assertEquals("14", actual);
    }

    @ParameterizedTest
    @ValueSource(ints = {3, 4, 5, 11, 17})
    void isFresh_shouldReturnTrue(int ingredient) {
        List<FreshRange> freshRanges = List.of(
                new FreshRange(3, 5),
                new FreshRange(10, 14),
                new FreshRange(16, 20),
                new FreshRange(12, 18)
        );

        assertTrue(new Day5().isFresh(ingredient, freshRanges));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 6, 8, 32})
    void isFresh_shouldReturnFalse(int ingredient) {
        List<FreshRange> freshRanges = List.of(
                new FreshRange(3, 5),
                new FreshRange(10, 14),
                new FreshRange(16, 20),
                new FreshRange(12, 18)
        );

        assertFalse(new Day5().isFresh(ingredient, freshRanges));
    }

    @Test
    void mergeRanges() {
        List<FreshRange> ranges = new ArrayList<>(List.of(
                new FreshRange(3, 5),
                new FreshRange(10, 14),
                new FreshRange(16, 20),
                new FreshRange(12, 18),
                new FreshRange(10, 12),
                new FreshRange(10, 14),
                new FreshRange(12, 13),
                new FreshRange(17, 20)
        ));
        List<FreshRange> expected = new ArrayList<>(List.of(
                new FreshRange(3, 5),
                new FreshRange(10, 20)
        ));
        List<FreshRange> actual = new Day5().mergeRanges(ranges);

        assertArrayEquals(expected.toArray(), actual.toArray());
    }

    @Test
    void readInput() throws IOException {
        IngredientDb actual = new Day5().readInput("src/test/resources/day5/example.txt");
        IngredientDb expected = new IngredientDb(
                List.of(
                        new FreshRange(3, 5),
                        new FreshRange(10, 14),
                        new FreshRange(16, 20),
                        new FreshRange(12, 18)
                ),
                List.of(1L, 5L, 8L, 11L, 17L, 32L)
        );

        assertArrayEquals(expected.freshRanges().toArray(), actual.freshRanges().toArray());
        assertArrayEquals(expected.ingredients().toArray(), actual.ingredients().toArray());
    }
}