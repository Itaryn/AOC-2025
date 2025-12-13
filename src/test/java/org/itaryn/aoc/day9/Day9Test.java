package org.itaryn.aoc.day9;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day9Test {

    @Test
    void getMaxArea() {
        List<Firepole> firepoles = List.of(
                new Firepole(7, 1),
                new Firepole(11, 1),
                new Firepole(11, 7),
                new Firepole(9, 7),
                new Firepole(9, 5),
                new Firepole(2, 5),
                new Firepole(2, 3),
                new Firepole(7, 3)
        );

        assertEquals(50, new Day9().getMaxArea(firepoles));
    }
}