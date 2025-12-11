package org.itaryn.aoc.day5;

import java.util.Objects;

public record FreshRange(long start, long end) {
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof FreshRange(long start1, long end1))) return false;
        return end == end1 && start == start1;
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end);
    }
}
