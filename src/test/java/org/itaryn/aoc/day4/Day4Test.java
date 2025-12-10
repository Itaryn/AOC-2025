package org.itaryn.aoc.day4;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class Day4Test {

    public static Stream<Arguments> isValidCoordinateProvider() {
        return Stream.of(
                Arguments.of(0, 0, true),
                Arguments.of(5, 5, true),
                Arguments.of(9, 9, true),
                Arguments.of(-1, 0, false),
                Arguments.of(0, -1, false),
                Arguments.of(10, 0, false),
                Arguments.of(0, 10, false)
        );
    }

    @Test
    void getFirstStar() throws IOException {
        assertEquals("13", new Day4().getFirstStar("src/test/resources/day4/example.txt"));
    }

    @Test
    void getSecondStar() throws IOException {
        assertEquals("43", new Day4().getSecondStar("src/test/resources/day4/example.txt"));
    }

    @Test
    void setHeat() {
        int[][] expected = {
                {0, 2, 2, 1},
                {3, 5, 3, 3},
                {1, 3, 3, 3},
                {2, 3, 3, 1}
        };

        List<List<GridPart>> grid = List.of(
                List.of(new GridPart(0, 0, true), new GridPart(0, 1, false), new GridPart(0, 2, false), new GridPart(0, 3, true)),
                List.of(new GridPart(1, 0, false), new GridPart(1, 1, false), new GridPart(1, 2, true), new GridPart(1, 3, false)),
                List.of(new GridPart(2, 0, true), new GridPart(2, 1, true), new GridPart(3, 2, true), new GridPart(2, 3, false)),
                List.of(new GridPart(3, 0, false), new GridPart(3, 1, false), new GridPart(4, 2, false), new GridPart(3, 3, true))
        );

        int[][] actual = new Day4().setHeat(grid).stream().map(line -> line.stream().mapToInt(GridPart::getHeat).toArray()).toArray(int[][]::new);

        assertArrayEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("isValidCoordinateProvider")
    void isValidCoordinate(int x, int y, boolean expected) {
        assertEquals(expected, new Day4().isValidCoordinate(x, y, 10, 10));
    }

    @Test
    void readInput() throws IOException {
        List<List<GridPart>> actual = new Day4().readInput("src/test/resources/day4/example.txt");

        assertFalse(actual.getFirst().get(0).hasPaperRoll());
        assertFalse(actual.getFirst().get(1).hasPaperRoll());
        assertTrue(actual.getFirst().get(2).hasPaperRoll());
        assertTrue(actual.getFirst().get(3).hasPaperRoll());

        assertTrue(actual.getLast().get(0).hasPaperRoll());
        assertFalse(actual.getLast().get(1).hasPaperRoll());
        assertTrue(actual.getLast().get(2).hasPaperRoll());
    }
}