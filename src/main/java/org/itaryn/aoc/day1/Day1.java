package org.itaryn.aoc.day1;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.itaryn.aoc.ExerciseHelper;
import org.itaryn.aoc.IExercise;
import picocli.CommandLine;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@ApplicationScoped
@CommandLine.Command(name = "day1", description = "Run Day 1 Exercise")
public class Day1 implements IExercise, Runnable {
    @Inject
    ExerciseHelper exerciseHelper;

    @Override
    public int getDay() {
        return 1;
    }

    @Override
    public String getFirstStar(String input) throws IOException {
        List<Command> commands = readInput(input);
        return getPasswordV1(commands);
    }

    @Override
    public String getSecondStar(String input) throws IOException {
        List<Command> commands = readInput(input);
        return getPasswordV2(commands);
    }

    @Override
    public void run() {
        exerciseHelper.runExercise(this);
    }

    protected String getPasswordV1(List<Command> commands) {
        int dialPosition = 50;
        int password = 0;
        for (Command command : commands) {
            dialPosition = getNewPosition(dialPosition, command);

            if (dialPosition == 0) {
                password++;
            }
        }
        return Integer.toString(password);
    }

    protected String getPasswordV2(List<Command> commands) {
        int dialPosition = 50;
        int password = 0;
        for (Command command : commands) {
            password += getPassedByZero(dialPosition, command);
            dialPosition = getNewPosition(dialPosition, command);
        }
        return Integer.toString(password);
    }

    protected List<Command> readInput(String filePath) throws IOException {
        return Files.readAllLines(Path.of(filePath)).stream().map(line ->
                new Command(Direction.fromCode(line.substring(0, 1)), Integer.parseInt(line.substring(1)))
        ).toList();
    }

    protected int getNewPosition(int dialPosition, Command command) {
        int tempPosition = dialPosition + command.getMovement();

        if (tempPosition > 99) {
            tempPosition = tempPosition % 100;
        } else if (tempPosition < 0) {
            tempPosition = ((tempPosition % 100) + 100) % 100;
        }

        return tempPosition;
    }

    protected int getPassedByZero(int dialPosition, Command command) {
        int zeros = command.distance() / 100;
        int modulo = command.getMovement() % 100;
        if (dialPosition != 0 && (dialPosition + modulo <= 0 || dialPosition + modulo > 99)) {
            zeros++;
        }

        return zeros;
    }
}
