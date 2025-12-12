package org.itaryn.aoc.day7;

import jakarta.enterprise.context.ApplicationScoped;
import org.itaryn.aoc.IExercise;
import picocli.CommandLine;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

import static java.util.stream.Collectors.groupingBy;

@ApplicationScoped
@CommandLine.Command(name = "day7", description = "Run Day 7 Exercise")
public class Day7 implements IExercise {
    @Override
    public int getDay() {
        return 7;
    }

    @Override
    public String getFirstStar(String input) throws IOException {
        return Integer.toString(countSplit(readInput(input)));
    }

    @Override
    public String getSecondStar(String input) throws IOException {
        return Long.toString(countParticles(readInput(input)));
    }

    protected long countParticles(TachyonManifold tachyonManifold) {
        List<Beam> beams = List.of(new Beam(tachyonManifold.start(), 1));
        for (Integer[] splitters : tachyonManifold.splitters()) {
            List<Beam> nextBeams = new ArrayList<>();
            for (Beam beam : beams) {
                if (Arrays.asList(splitters).contains(beam.getPosition())) {
                    nextBeams.add(new Beam(beam.getPosition() - 1, beam.getParticles()));
                    nextBeams.add(new Beam(beam.getPosition() + 1, beam.getParticles()));
                } else {
                    nextBeams.add(beam);
                }
            }
            beams = nextBeams.stream()
                    .collect(groupingBy(Beam::getPosition))
                    .entrySet().stream().map(entry ->
                            new Beam(
                                    entry.getKey(),
                                    entry.getValue().stream().map(Beam::getParticles).reduce(Long::sum).orElse(1L))).toList();
        }
        return beams.stream().mapToLong(Beam::getParticles).sum();
    }

    protected int countSplit(TachyonManifold tachyonManifold) {
        Set<Integer> beams = Set.of(tachyonManifold.start());
        int split = 0;
        for (Integer[] splitters : tachyonManifold.splitters()) {
            Set<Integer> nextBeams = new HashSet<>();
            for (Integer beam : beams) {
                if (Arrays.asList(splitters).contains(beam)) {
                    nextBeams.add(beam - 1);
                    nextBeams.add(beam + 1);
                    split++;
                } else {
                    nextBeams.add(beam);
                }
            }
            beams = nextBeams;
        }
        return split;
    }

    protected TachyonManifold readInput(String input) throws IOException {
        List<String> lines = Files.readAllLines(Path.of(input));
        int start = lines.getFirst().indexOf('S');
        Integer[][] splittersByLine = lines.stream().skip(1).map(line -> {
            List<Integer> splitters = new ArrayList<>();
            for (int i = 0; i < line.length(); i++) {
                if (line.charAt(i) == '^') {
                    splitters.add(i);
                }
            }
            return splitters.toArray(Integer[]::new);
        }).toArray(Integer[][]::new);
        return new TachyonManifold(start, splittersByLine);
    }
}
