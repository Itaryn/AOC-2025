package org.itaryn.aoc.day8;

import jakarta.enterprise.context.ApplicationScoped;
import org.itaryn.aoc.IExercise;
import picocli.CommandLine;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

@ApplicationScoped
@CommandLine.Command(name = "day8", description = "Run Day 8 Exercise")
public class Day8 implements IExercise {
    private static void link(PositionPair pair, List<Set<Position>> linkedPositions) {
        Optional<Set<Position>> existingGroupA = linkedPositions.stream()
                .filter(group -> group.contains(pair.a()))
                .findFirst();
        Optional<Set<Position>> existingGroupB = linkedPositions.stream()
                .filter(group -> group.contains(pair.b()))
                .findFirst();
        if (existingGroupA.isEmpty() && existingGroupB.isEmpty()) {
            linkedPositions.add(new HashSet<>(Set.of(pair.a(), pair.b())));
        } else if (existingGroupA.isPresent() && existingGroupB.isEmpty()) {
            existingGroupA.get().add(pair.b());
        } else if (existingGroupA.isEmpty()) {
            existingGroupB.get().add(pair.a());
        } else if (existingGroupA.get() != existingGroupB.get()) {
            existingGroupA.get().addAll(existingGroupB.get());
            linkedPositions.remove(existingGroupB.get());
        }
    }

    @Override
    public int getDay() {
        return 8;
    }

    @Override
    public String getFirstStar(String input) throws IOException {
        return Long.toString(
                linkPositions(calculateAllDistances(readInput(input)), 1000).stream()
                        .map(Set::size)
                        .sorted(Comparator.reverseOrder())
                        .limit(3)
                        .reduce((a, b) -> a * b).orElse(0));
    }

    @Override
    public String getSecondStar(String input) throws IOException {
        List<Position> positions = readInput(input);
        PositionPair lastPair = getLastPair(calculateAllDistances(positions), positions.size());
        return String.valueOf(lastPair.a().x() * lastPair.b().x());
    }

    protected PositionPair getLastPair(List<PositionPair> positionPairs, int totalPosition) {
        List<Set<Position>> linkedPositions = new ArrayList<>();
        int i = -1;
        do {
            i++;
            link(positionPairs.get(i), linkedPositions);
        }
        while (linkedPositions.get(0).size() < totalPosition);

        return positionPairs.get(i);
    }

    protected List<Set<Position>> linkPositions(List<PositionPair> positionPairs, int round) {
        List<Set<Position>> linkedPositions = new ArrayList<>();
        for (PositionPair pair : positionPairs.stream().limit(round).toList()) {
            link(pair, linkedPositions);
        }
        return linkedPositions;
    }

    protected List<PositionPair> calculateAllDistances(List<Position> positions) {
        List<PositionPair> positionPairs = new ArrayList<>();
        for (int i = 0; i < positions.size(); i++) {
            Position a = positions.get(i);
            for (int j = i + 1; j < positions.size(); j++) {
                Position b = positions.get(j);
                positionPairs.add(new PositionPair(a, b, distance(a, b)));
            }
        }
        positionPairs.sort(Comparator.comparingDouble(PositionPair::distance));
        return positionPairs;
    }

    protected double distance(Position a, Position b) {
        return Math.sqrt(
                Math.pow(b.x() - a.x(), 2) +
                        Math.pow(b.y() - a.y(), 2) +
                        Math.pow(b.z() - a.z(), 2)
        );
    }

    protected List<Position> readInput(String input) throws IOException {
        return Files.readAllLines(Path.of(input)).stream().map(line -> {
            String[] parts = line.split(",");
            return new Position(
                    Integer.parseInt(parts[0]),
                    Integer.parseInt(parts[1]),
                    Integer.parseInt(parts[2])
            );
        }).toList();
    }
}
