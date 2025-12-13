package org.itaryn.aoc.day8;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day8Test {

    @Test
    void getLastPair() throws IOException {
        List<Position> positions = new Day8().readInput("src/test/resources/day8/example.txt");
        PositionPair actual = new Day8().getLastPair(new Day8().calculateAllDistances(positions), positions.size());

        assertEquals(new Position(216, 146, 977), actual.a());
        assertEquals(new Position(117, 168, 530), actual.b());
    }

    @Test
    void linkPositions() throws IOException {
        List<Integer> actual = new Day8()
                .linkPositions(new Day8().calculateAllDistances(new Day8().readInput("src/test/resources/day8/example.txt")), 10)
                .stream()
                .map(Set::size)
                .sorted(Comparator.reverseOrder())
                .toList();

        assertEquals(5, actual.get(0));
        assertEquals(4, actual.get(1));
        assertEquals(2, actual.get(2));
    }

    @Test
    void calculateAllDistances() throws IOException {
        List<PositionPair> actual = new Day8().calculateAllDistances(new Day8().readInput("src/test/resources/day8/example.txt"));

        assertEquals(new Position(162, 817, 812), actual.get(0).a());
        assertEquals(new Position(425, 690, 689), actual.get(0).b());

        assertEquals(new Position(162, 817, 812), actual.get(1).a());
        assertEquals(new Position(431, 825, 988), actual.get(1).b());

        assertEquals(new Position(906, 360, 560), actual.get(2).a());
        assertEquals(new Position(805, 96, 715), actual.get(2).b());

        assertEquals(new Position(431, 825, 988), actual.get(3).a());
        assertEquals(new Position(425, 690, 689), actual.get(3).b());
    }
}