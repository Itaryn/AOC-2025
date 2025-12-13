package org.itaryn.aoc.day9;

import jakarta.enterprise.context.ApplicationScoped;
import org.itaryn.aoc.IExercise;
import picocli.CommandLine;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@ApplicationScoped
@CommandLine.Command(name = "day9", description = "Run Day 9 Exercise")
public class Day9 implements IExercise {
    @Override
    public int getDay() {
        return 9;
    }

    @Override
    public String getFirstStar(String input) throws IOException {
        return String.valueOf(getMaxArea(readInput(input)));
    }

    @Override
    public String getSecondStar(String input) throws IOException {
        return "";
    }

    protected long getMaxArea(List<Firepole> firepoles) {
        long max = 0;
        for (int i = 0; i < firepoles.size(); i++) {
            Firepole poleA = firepoles.get(i);
            for (int j = i + 1; j < firepoles.size(); j++) {
                Firepole poleB = firepoles.get(j);
                long area = (Math.abs(poleA.x() - poleB.x()) + 1) * (Math.abs(poleA.y() - poleB.y()) + 1);
                if (area > max) {
                    max = area;
                }
            }
        }
        return max;
    }

    protected List<Firepole> readInput(String input) throws IOException {
        return Files.readAllLines(Path.of(input)).stream().map(line -> {
            String[] parts = line.split(",");
            return new Firepole(
                    Long.parseLong(parts[0]),
                    Long.parseLong(parts[1])
            );
        }).toList();
    }
}
