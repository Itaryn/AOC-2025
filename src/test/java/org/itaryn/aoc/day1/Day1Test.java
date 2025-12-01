package org.itaryn.aoc.day1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertLinesMatch;

public class Day1Test {

    public static Stream<Arguments> positionAndResultProvider() {
        return Stream.of(
                Arguments.of(50, new Command(Direction.PLUS, 20), 70),
                Arguments.of(50, new Command(Direction.MINUS, 20), 30),
                Arguments.of(50, new Command(Direction.PLUS, 50), 0),
                Arguments.of(50, new Command(Direction.MINUS, 50), 0),
                Arguments.of(50, new Command(Direction.PLUS, 70), 20),
                Arguments.of(50, new Command(Direction.PLUS, 150), 0),
                Arguments.of(50, new Command(Direction.MINUS, 51), 99),
                Arguments.of(50, new Command(Direction.PLUS, 170), 20),
                Arguments.of(50, new Command(Direction.PLUS, 370), 20),
                Arguments.of(50, new Command(Direction.MINUS, 151), 99),
                Arguments.of(50, new Command(Direction.MINUS, 351), 99),
                Arguments.of(50, new Command(Direction.MINUS, 200), 50),
                Arguments.of(82, new Command(Direction.MINUS, 182), 0));
    }

    public static Stream<Arguments> positionAndCount0Provider() {
        return Stream.of(
                Arguments.of(50, new Command(Direction.PLUS, 20), 0),
                Arguments.of(50, new Command(Direction.MINUS, 20), 0),
                Arguments.of(50, new Command(Direction.PLUS, 50), 1),
                Arguments.of(50, new Command(Direction.MINUS, 50), 1),
                Arguments.of(50, new Command(Direction.PLUS, 70), 1),
                Arguments.of(50, new Command(Direction.PLUS, 150), 2),
                Arguments.of(50, new Command(Direction.MINUS, 51), 1),
                Arguments.of(50, new Command(Direction.PLUS, 170), 2),
                Arguments.of(50, new Command(Direction.PLUS, 370), 4),
                Arguments.of(50, new Command(Direction.MINUS, 151), 2),
                Arguments.of(50, new Command(Direction.MINUS, 351), 4),
                Arguments.of(50, new Command(Direction.MINUS, 200), 2),
                Arguments.of(0, new Command(Direction.PLUS, 200), 2),
                Arguments.of(0, new Command(Direction.MINUS, 200), 2),
                Arguments.of(0, new Command(Direction.PLUS, 10), 0),
                Arguments.of(0, new Command(Direction.MINUS, 10), 0),
                Arguments.of(0, new Command(Direction.PLUS, 110), 1),
                Arguments.of(0, new Command(Direction.MINUS, 110), 1),
                Arguments.of(10, new Command(Direction.PLUS, 190), 2),
                Arguments.of(10, new Command(Direction.MINUS, 110), 2),
                Arguments.of(10, new Command(Direction.MINUS, 5), 0),
                Arguments.of(10, new Command(Direction.MINUS, 10), 1),
                Arguments.of(10, new Command(Direction.MINUS, 15), 1),
                Arguments.of(10, new Command(Direction.MINUS, 30), 1),
                Arguments.of(10, new Command(Direction.MINUS, 80), 1),
                Arguments.of(10, new Command(Direction.MINUS, 100), 1),
                Arguments.of(10, new Command(Direction.MINUS, 109), 1),
                Arguments.of(10, new Command(Direction.MINUS, 110), 2),
                Arguments.of(90, new Command(Direction.PLUS, 9), 0),
                Arguments.of(90, new Command(Direction.PLUS, 10), 1),
                Arguments.of(90, new Command(Direction.PLUS, 11), 1),
                Arguments.of(90, new Command(Direction.PLUS, 30), 1),
                Arguments.of(90, new Command(Direction.PLUS, 80), 1),
                Arguments.of(90, new Command(Direction.PLUS, 100), 1),
                Arguments.of(90, new Command(Direction.PLUS, 109), 1),
                Arguments.of(90, new Command(Direction.PLUS, 110), 2),
                Arguments.of(90, new Command(Direction.PLUS, 209), 2),
                Arguments.of(90, new Command(Direction.PLUS, 210), 3),
                Arguments.of(50, new Command(Direction.PLUS, 1000), 10));
    }

    @Test
    void getPasswordV1() {
        List<Command> commands = List.of(
                new Command(Direction.MINUS, 68),
                new Command(Direction.MINUS, 30),
                new Command(Direction.PLUS, 48),
                new Command(Direction.MINUS, 5),
                new Command(Direction.PLUS, 60),
                new Command(Direction.MINUS, 55),
                new Command(Direction.MINUS, 1),
                new Command(Direction.MINUS, 99),
                new Command(Direction.PLUS, 14),
                new Command(Direction.MINUS, 82)
        );

        assertEquals("3", new Day1().getPasswordV1(commands));
    }

    @Test
    void getPasswordV2() {
        List<Command> commands = List.of(
                new Command(Direction.MINUS, 68),
                new Command(Direction.MINUS, 30),
                new Command(Direction.PLUS, 48),
                new Command(Direction.MINUS, 5),
                new Command(Direction.PLUS, 60),
                new Command(Direction.MINUS, 55),
                new Command(Direction.MINUS, 1),
                new Command(Direction.MINUS, 99),
                new Command(Direction.PLUS, 14),
                new Command(Direction.MINUS, 82)
        );

        assertEquals("6", new Day1().getPasswordV2(commands));
    }

    @ParameterizedTest
    @MethodSource("positionAndResultProvider")
    void getNewPosition(int dialPosition, Command command, int expectedPosition) {
        assertEquals(expectedPosition, new Day1().getNewPosition(dialPosition, command));
    }

    @ParameterizedTest
    @MethodSource("positionAndCount0Provider")
    void getPassedByZero(int dialPosition, Command command, int expectedZero) {
        assertEquals(expectedZero, new Day1().getPassedByZero(dialPosition, command));
    }

    @Test
    void readInput() throws IOException {
        List<Command> expectedCommands = List.of(
                new Command(Direction.MINUS, 68),
                new Command(Direction.MINUS, 30),
                new Command(Direction.PLUS, 48),
                new Command(Direction.MINUS, 5),
                new Command(Direction.PLUS, 60),
                new Command(Direction.MINUS, 55),
                new Command(Direction.MINUS, 1),
                new Command(Direction.MINUS, 99),
                new Command(Direction.PLUS, 14),
                new Command(Direction.MINUS, 82)
        );

        List<Command> actualCommands = new Day1().readInput("src/test/resources/day1/example.txt");
        assertEquals(10, actualCommands.size());
        assertLinesMatch(
                expectedCommands.stream().map(Command::toString).toList(),
                actualCommands.stream().map(Command::toString).toList());
    }
}