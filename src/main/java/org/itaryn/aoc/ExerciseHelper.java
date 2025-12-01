package org.itaryn.aoc;

import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;

import java.io.IOException;

@ApplicationScoped
public class ExerciseHelper {
    private static final String INPUT = "src/main/resources/day%d/input.txt";

    public void runExercise(IExercise exercise) {
        String input = INPUT.formatted(exercise.getDay());

        try {
            Log.infof("Day %d, first star is '%s', second star is '%s'",
                    exercise.getDay(),
                    exercise.getFirstStar(input),
                    exercise.getSecondStar(input));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
