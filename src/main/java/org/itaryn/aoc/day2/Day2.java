package org.itaryn.aoc.day2;

import jakarta.enterprise.context.ApplicationScoped;
import org.itaryn.aoc.IExercise;
import picocli.CommandLine;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.function.LongPredicate;
import java.util.regex.Pattern;

@ApplicationScoped
@CommandLine.Command(name = "day2", description = "Run Day 2 Exercise")
public class Day2 implements IExercise {
    protected static boolean isSimpleValidId(long productId) {
        boolean isValid = true;
        String idStr = Long.toString(productId);
        if (idStr.length() % 2 == 0) {
            String leftPart = idStr.substring(0, idStr.length() / 2);
            String rightPart = idStr.substring(idStr.length() / 2);
            if (leftPart.equals(rightPart)) {
                isValid = false;
            }
        }
        return isValid;
    }

    protected static boolean isComplexValidId(long productId) {
        boolean isValid = true;
        String idStr = Long.toString(productId);
        int i = 1;
        while (isValid && i <= idStr.length() / 2) {
            if (idStr.length() % i == 0) {
                Pattern pattern = Pattern.compile("^(" + idStr.substring(0, i) + "){2,}$");
                if (pattern.matcher(idStr).matches()) {
                    isValid = false;
                }
            }
            i++;
        }
        return isValid;
    }

    @Override
    public int getDay() {
        return 2;
    }

    @Override
    public String getFirstStar(String input) throws IOException {
        return String.format("%.0f", sumInvalidProductIds(readInput(input), Day2::isSimpleValidId));
    }

    @Override
    public String getSecondStar(String input) throws IOException {
        return String.format("%.0f", sumInvalidProductIds(readInput(input), Day2::isComplexValidId));
    }

    protected double sumInvalidProductIds(List<ProductRange> productRanges, LongPredicate isValidFunc) {
        return productRanges.stream()
                .flatMapToLong(range ->
                        java.util.stream.LongStream.rangeClosed(range.start(), range.end()))
                .filter(productId -> !isValidFunc.test(productId))
                .asDoubleStream()
                .sum();
    }

    protected List<ProductRange> readInput(String input) throws IOException {
        return Arrays.stream(Files.readString(Path.of(input)).split(","))
                .map(productStr -> {
                    String[] productParts = productStr.split("-");
                    return new ProductRange(Long.parseLong(productParts[0]), Long.parseLong(productParts[1]));
                }).toList();
    }
}
