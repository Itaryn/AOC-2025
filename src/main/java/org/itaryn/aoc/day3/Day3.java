package org.itaryn.aoc.day3;

import jakarta.enterprise.context.ApplicationScoped;
import org.itaryn.aoc.IExercise;
import picocli.CommandLine;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@ApplicationScoped
@CommandLine.Command(name = "day3", description = "Run Day 3 Exercise")
public class Day3 implements IExercise {
    @Override
    public int getDay() {
        return 3;
    }

    @Override
    public String getFirstStar(String input) throws IOException {
        return Long.toString(readInput(input).stream().mapToLong(bank -> getJolts(bank, 2)).sum());
    }

    @Override
    public String getSecondStar(String input) throws IOException {
        return Long.toString(readInput(input).stream().mapToLong(bank -> getJolts(bank, 12)).sum());
    }

    protected long getJolts(String bank, int numberOfBattery) {
        assert bank.length() >= numberOfBattery;
        long jolts = 0;
        for (int i = numberOfBattery - 1; i >= 0; i--) {
            Battery battery = getGreaterLeftestBattery(bank.substring(0, bank.length() - i));
            bank = bank.substring(battery.getIndex() + 1);
            jolts += (long) (Character.getNumericValue(battery.getJoltage()) * Math.pow(10, i));
        }
        return jolts;
    }

    protected Battery getGreaterLeftestBattery(String str) {
        Battery greaterBattery = new Battery()
                .setJoltage(str.charAt(0)).setIndex(0);
        for (int i = 1; i < str.length(); i++) {
            if (greaterBattery.getJoltage() < str.charAt(i)) {
                greaterBattery.setJoltage(str.charAt(i)).setIndex(i);
            }
        }
        return greaterBattery;
    }

    protected List<String> readInput(String input) throws IOException {
        return Files.readAllLines(Path.of(input));
    }
}
