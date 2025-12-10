package org.itaryn.aoc.day4;

import java.util.Objects;

public class GridPart {
    private int x;
    private int y;
    private boolean paperRoll;
    private int heatValue;

    public GridPart(int x, int y, boolean paperRoll) {
        this.x = x;
        this.y = y;
        this.paperRoll = paperRoll;
        this.heatValue = 0;
    }

    public int getX() {
        return x;
    }

    public GridPart setX(int x) {
        this.x = x;
        return this;
    }

    public int getY() {
        return y;
    }

    public GridPart setY(int y) {
        this.y = y;
        return this;
    }

    public boolean hasPaperRoll() {
        return paperRoll;
    }

    public GridPart setPaperRoll(boolean paperRoll) {
        this.paperRoll = paperRoll;
        return this;
    }

    public int getHeat() {
        return heatValue;
    }

    public GridPart addHeat() {
        this.heatValue++;
        return this;
    }

    public void resetHeat() {
        this.heatValue = 0;
    }

    @Override
    public String toString() {
        return "GridPart{" +
                "x=" + x +
                ", y=" + y +
                ", paperRoll=" + paperRoll +
                ", heatValue=" + heatValue +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof GridPart gridPart)) return false;
        return x == gridPart.x && y == gridPart.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
