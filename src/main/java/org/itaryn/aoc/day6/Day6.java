package org.itaryn.aoc.day6;

import jakarta.enterprise.context.ApplicationScoped;
import org.itaryn.aoc.IExercise;
import picocli.CommandLine;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
@CommandLine.Command(name = "day6", description = "Run Day 6 Exercise")
public class Day6 implements IExercise {
    @Override
    public int getDay() {
        return 6;
    }

    @Override
    public String getFirstStar(String input) throws IOException {
        return readInput(input).stream().map(this::calcul).reduce(Long::sum).orElse(0L).toString();
    }

    @Override
    public String getSecondStar(String input) throws IOException {
        return readInput(input).stream().map(calc -> {
            List<Long> numbers = getNumbersRightToLeft(calc);
            return new Calculation(numbers.stream().map(Object::toString).toList(), calc.operationStr());
        }).map(this::calcul).reduce(Long::sum).orElse(0L).toString();
    }

    protected List<Long> getNumbersRightToLeft(Calculation calculation) {
        List<Long> results = new ArrayList<>();
        for (int pos = calculation.operationStr().length() - 1; pos >= 0; pos--) {
            StringBuilder currentNumber = new StringBuilder();
            for (int i = 0; i < calculation.values().size(); i++) {
                String numberStr = calculation.valuesStr().get(i);
                if (numberStr.charAt(pos) != ' ') {
                    currentNumber.append(numberStr.charAt(pos));
                }
            }
            results.add(Long.parseLong(currentNumber.toString()));
        }
        return results;
    }

    protected Long calcul(Calculation calculation) {
        return switch (calculation.operation()) {
            case '+' -> calculation.values().stream().reduce(Long::sum).orElse(0L);
            case '*' -> calculation.values().stream().reduce((a, b) -> a * b).orElse(1L);
            default -> throw new IllegalArgumentException("Unknown operation: " + calculation.operation());
        };
    }

    protected List<Calculation> readInput(String input) throws IOException {
        List<String> rawCalculations = Files.readAllLines(Path.of(input));
        List<Calculation> calculations = new ArrayList<>();
        String[] operations = rawCalculations.getLast().split("(?=[+*])");
        for (int i = 0; i < operations.length - 1; i++) {
            operations[i] = operations[i].substring(0, operations[i].length() - 1);
        }
        List<List<String>> values = new ArrayList<>();
        for (int i = rawCalculations.size() - 2; i >= 0; i--) {
            List<String> rowValues = new ArrayList<>();
            int index = 0;
            for (String operation : operations) {
                rowValues.add(rawCalculations.get(i).substring(index, index + operation.length()));
                index += operation.length() + 1;
            }
            values.add(rowValues);
        }
        values = values.reversed();
        for (int i = 0; i < operations.length; i++) {
            List<String> calculValues = new ArrayList<>();
            for (List<String> rowValues : values) {
                calculValues.add(rowValues.get(i));
            }
            calculations.add(new Calculation(calculValues, operations[i]));
        }
        return calculations;
    }
}

