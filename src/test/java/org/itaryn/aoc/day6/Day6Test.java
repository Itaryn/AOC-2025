package org.itaryn.aoc.day6;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Day6Test {

    public static Stream<Arguments> calculProvider() {
        return Stream.of(
                Arguments.of(new Calculation(List.of("123", " 45", "  6"), "*  "), 33210L),
                Arguments.of(new Calculation(List.of("328", "64 ", "98 "), "+  "), 490L),
                Arguments.of(new Calculation(List.of(" 51", "387", "215"), "*  "), 4243455L),
                Arguments.of(new Calculation(List.of("64 ", "23 ", "314"), "+  "), 401L)
        );
    }

    public static Stream<Arguments> getNumbersRightToLeftProvider() {
        return Stream.of(
                Arguments.of(new Calculation(List.of("123", " 45", "  6"), "*  "), List.of(356L, 24L, 1L)),
                Arguments.of(new Calculation(List.of("328", "64 ", "98 "), "+  "), List.of(8L, 248L, 369L)),
                Arguments.of(new Calculation(List.of(" 51", "387", "215"), "*  "), List.of(175L, 581L, 32L)),
                Arguments.of(new Calculation(List.of("64 ", "23 ", "314"), "+  "), List.of(4L, 431L, 623L))
        );
    }

    @Test
    void getFirstStar() throws IOException {
        String actual = new Day6().getFirstStar("src/test/resources/day6/example.txt");
        assertEquals("4277556", actual);
    }

    @ParameterizedTest
    @MethodSource("calculProvider")
    void calcul(Calculation calculation, Long expected) {
        Long actual = new Day6().calcul(calculation);
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("getNumbersRightToLeftProvider")
    void getNumbersRightToLeft(Calculation calculation, List<Long> expected) {
        List<Long> actual = new Day6().getNumbersRightToLeft(calculation);
        assertArrayEquals(expected.toArray(), actual.toArray());
    }

    @Test
    void readInput() throws IOException {
        List<Calculation> expected = List.of(
                new Calculation(List.of("123", " 45", "  6"), "*  "),
                new Calculation(List.of("328", "64 ", "98 "), "+  "),
                new Calculation(List.of(" 51", "387", "215"), "*  "),
                new Calculation(List.of("64 ", "23 ", "314"), "+  ")
        );
        List<Calculation> actual = new Day6().readInput("src/test/resources/day6/example.txt");

        assertArrayEquals(expected.toArray(), actual.toArray());
    }
}