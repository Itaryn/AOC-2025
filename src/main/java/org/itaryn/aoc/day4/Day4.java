package org.itaryn.aoc.day4;

import jakarta.enterprise.context.ApplicationScoped;
import org.itaryn.aoc.IExercise;
import picocli.CommandLine;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
@CommandLine.Command(name = "day4", description = "Run Day 4 Exercise")
public class Day4 implements IExercise {
    @Override
    public int getDay() {
        return 4;
    }

    @Override
    public String getFirstStar(String input) throws IOException {
        return Long.toString(setHeat(readInput(input)).stream()
                .flatMap(List::stream)
                .filter(part -> part.hasPaperRoll() && part.getHeat() < 4)
                .count());
    }

    @Override
    public String getSecondStar(String input) throws IOException {
        List<List<GridPart>> grid = readInput(input);
        int removed;
        int paperRollRemoved = 0;
        do {
            setHeat(grid);
            removed = removePaperRolls(grid);
            paperRollRemoved += removed;
        } while (removed != 0);

        return Integer.toString(paperRollRemoved);
    }

    protected int removePaperRolls(List<List<GridPart>> grid) {
        int removed = 0;
        for (GridPart part : grid.stream().flatMap(List::stream).toList()) {
            if (part.hasPaperRoll() && part.getHeat() < 4) {
                removed++;
                part.setPaperRoll(false);
            }
            part.resetHeat();
        }
        return removed;
    }

    protected List<List<GridPart>> setHeat(List<List<GridPart>> grid) {
        for (int x = 0; x < grid.size(); x++) {
            for (int y = 0; y < grid.get(x).size(); y++) {
                if (grid.get(x).get(y).hasPaperRoll()) {
                    addHeat(grid, x, y);
                }
            }
        }
        return grid;
    }

    private void addHeat(List<List<GridPart>> grid, int x, int y) {
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (isValidCoordinate(x + i, y + j, grid.size(), grid.get(x).size()) &&
                        !(i == 0 && j == 0)) {
                    grid.get(x + i).get(y + j).addHeat();
                }
            }
        }
    }

    protected boolean isValidCoordinate(int x, int y, int xMax, int yMax) {
        return x >= 0 && x < xMax && y >= 0 && y < yMax;
    }

    protected List<List<GridPart>> readInput(String input) throws IOException {
        List<List<GridPart>> grid = new ArrayList<>();
        List<String> lines = Files.readAllLines(Path.of(input));
        for (int x = 0; x < lines.size(); x++) {
            List<GridPart> line = new ArrayList<>();
            for (int y = 0; y < lines.get(x).length(); y++) {
                line.add(new GridPart(x, y, lines.get(x).charAt(y) == '@'));
            }
            grid.add(line);
        }
        return grid;
    }
}

