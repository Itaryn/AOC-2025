package org.itaryn.aoc.day3;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertLinesMatch;

class Day3Test {

    public static Stream<Arguments> greaterLeftestBatteryProvider() {
        return Stream.of(
                Arguments.of("123456789", '9', 8),
                Arguments.of("987654321", '9', 0),
                Arguments.of("111119111", '9', 5),
                Arguments.of("123123123", '3', 2)
        );
    }

    public static Stream<Arguments> getJoltsProvider() {
        return Stream.of(
                Arguments.of("123456789", 89, 2),
                Arguments.of("987654321", 98, 2),
                Arguments.of("111119111", 91, 2),
                Arguments.of("123123123", 33, 2),
                Arguments.of("987654321111111", 987654321111L, 12),
                Arguments.of("811111111111119", 811111111119L, 12),
                Arguments.of("234234234234278", 434234234278L, 12),
                Arguments.of("818181911112111", 888911112111L, 12)
        );
    }

    @ParameterizedTest
    @MethodSource("getJoltsProvider")
    void getJolts(String bank, long expectedJolts, int batteries) {
        long actualJolts = new Day3().getJolts(bank, batteries);
        assertEquals(expectedJolts, actualJolts);
    }

    @ParameterizedTest
    @MethodSource("greaterLeftestBatteryProvider")
    void getGreaterLeftestBattery(String bank, char expectedJoltage, int expectedIndex) {
        Battery battery = new Day3().getGreaterLeftestBattery(bank);
        assertEquals(expectedJoltage, battery.getJoltage());
        assertEquals(expectedIndex, battery.getIndex());
    }

    @Test
    void readInput() throws IOException {
        List<String> expected = List.of(
                "987654321111111",
                "811111111111119",
                "234234234234278",
                "818181911112111"
        );
        List<String> actual = new Day3().readInput("src/test/resources/day3/example.txt");
        assertLinesMatch(expected, actual);
    }
}