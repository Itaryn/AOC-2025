package org.itaryn.aoc;

import java.io.IOException;

public interface IExercise {
    int getDay();
    String getFirstStar(String input) throws IOException;
    String getSecondStar(String input) throws IOException;
}
