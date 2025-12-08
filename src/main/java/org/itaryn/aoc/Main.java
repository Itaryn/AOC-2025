package org.itaryn.aoc;

import io.quarkus.picocli.runtime.annotations.TopCommand;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;
import picocli.CommandLine;

@TopCommand
@CommandLine.Command(name = "all", description = "Run all exercises")
public class Main implements Runnable {
    private final Instance<IExercise> days;
    private final ExerciseHelper exerciseHelper;

    @Inject
    public Main(Instance<IExercise> days, ExerciseHelper exerciseHelper) {
        this.days = days;
        this.exerciseHelper = exerciseHelper;
    }

    @Override
    public void run() {
        days.forEach(exerciseHelper::runExercise);
    }
}
