package org.itaryn.aoc;

import io.quarkus.picocli.runtime.annotations.TopCommand;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;
import picocli.CommandLine;

import java.io.IOException;

@TopCommand
@CommandLine.Command(name = "all", description = "Run all exercises")
public class Main implements Runnable {
    @Inject
    Instance<IExercise> days;

    @Inject
    ExerciseHelper exerciseHelper;

    @Override
    public void run() {
        days.forEach(exercise -> exerciseHelper.runExercise(exercise));
    }
}
