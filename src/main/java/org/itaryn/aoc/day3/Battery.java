package org.itaryn.aoc.day3;

public class Battery {
    private char joltage;
    private int index;

    public char getJoltage() {
        return joltage;
    }

    public Battery setJoltage(char joltage) {
        this.joltage = joltage;
        return this;
    }

    public int getIndex() {
        return index;
    }

    public Battery setIndex(int index) {
        this.index = index;
        return this;
    }
}
