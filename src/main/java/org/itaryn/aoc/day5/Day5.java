package org.itaryn.aoc.day5;

import jakarta.enterprise.context.ApplicationScoped;
import org.itaryn.aoc.IExercise;
import picocli.CommandLine;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
@CommandLine.Command(name = "day5", description = "Run Day 5 Exercise")
public class Day5 implements IExercise {
    @Override
    public int getDay() {
        return 5;
    }

    @Override
    public String getFirstStar(String input) throws IOException {
        IngredientDb db = readInput(input);
        return Long.toString(db.ingredients().stream()
                .map(ingredient -> isFresh(ingredient, db.freshRanges()))
                .filter(fresh -> fresh)
                .count()
        );
    }

    @Override
    public String getSecondStar(String input) throws IOException {
        List<FreshRange> ranges = mergeRanges(readInput(input).freshRanges());
        return ranges.stream().map(range -> (range.end() - range.start() + 1)).reduce(Long::sum).orElse(0L).toString();
    }

    protected List<FreshRange> mergeRanges(List<FreshRange> freshRanges) {
        boolean merged;
        do {
            merged = false;
            int i = 0;
            while (i < freshRanges.size()) {
                FreshRange range1 = freshRanges.get(i);
                int j = i + 1;
                while (j < freshRanges.size()) {
                    FreshRange range2 = freshRanges.get(j);
                    if (range1.start() >= range2.start() && range1.start() <= range2.end()) {
                        range1 = updateFreshRanges(freshRanges, range2.start(), range1.end(), range2.end(), i, j);
                        merged = true;
                    } else if (range2.start() >= range1.start() && range2.start() <= range1.end()) {
                        range1 = updateFreshRanges(freshRanges, range1.start(), range1.end(), range2.end(), i, j);
                        merged = true;
                    } else {
                        j++;
                    }
                }
                i++;
            }
        } while (merged);
        return freshRanges;
    }

    private FreshRange updateFreshRanges(List<FreshRange> freshRanges, long start, long end1, long end2, int setAt, int removeAt) {
        long end = Math.max(end1, end2);
        FreshRange range = new FreshRange(start, end);
        freshRanges.set(setAt, range);
        freshRanges.remove(removeAt);
        return range;
    }

    protected boolean isFresh(long ingredient, List<FreshRange> freshRanges) {
        boolean result = false;
        int i = 0;
        while (!result && i < freshRanges.size()) {
            FreshRange range = freshRanges.get(i);
            if (ingredient >= range.start() && ingredient <= range.end()) {
                result = true;
            }
            i++;
        }
        return result;
    }

    protected IngredientDb readInput(String input) throws IOException {
        List<FreshRange> freshRanges = new ArrayList<>();
        List<Long> ingredients = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(Path.of(input))) {
            String line = reader.readLine();
            while (!line.isBlank()) {
                String[] parts = line.split("-");
                freshRanges.add(new FreshRange(Long.parseLong(parts[0]), Long.parseLong(parts[1])));
                line = reader.readLine();
            }
            line = reader.readLine();
            while (line != null) {
                ingredients.add(Long.parseLong(line));
                line = reader.readLine();
            }
        }
        return new IngredientDb(freshRanges, ingredients);
    }
}

