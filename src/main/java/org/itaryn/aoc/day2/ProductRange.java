package org.itaryn.aoc.day2;

public record ProductRange(long start, long end) {
    @Override
    public String toString() {
        return "ProductRange{" +
                "start=" + start +
                ", end=" + end +
                '}';
    }
}
