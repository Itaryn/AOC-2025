package org.itaryn.aoc.day1;

public record Command(Direction direction, int distance) {
    int getMovement() {
        return switch (direction) {
            case Direction.PLUS -> distance;
            case Direction.MINUS -> -distance;
        };
    }

    @Override
    public String toString() {
        return "Command{" +
                "direction=" + direction +
                ", distance=" + distance +
                '}';
    }
}
