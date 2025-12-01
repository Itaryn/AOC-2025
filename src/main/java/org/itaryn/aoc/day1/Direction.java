package org.itaryn.aoc.day1;

public enum Direction {
    MINUS("L"),
    PLUS("R");
    private final String code;
    Direction(String code) {
        this.code = code;
    }

    public static Direction fromCode(String substring) {
        return switch (substring) {
            case "L" -> MINUS;
            case "R" -> PLUS;
            default -> throw new IllegalArgumentException("Unknown direction code: " + substring);
        };
    }
}
