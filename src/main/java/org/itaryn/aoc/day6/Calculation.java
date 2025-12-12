package org.itaryn.aoc.day6;

import java.util.List;

public record Calculation(List<String> valuesStr, String operationStr) {
    List<Long> values() {
        return valuesStr.stream().map(String::trim).map(Long::parseLong).toList();
    }

    char operation() {
        return operationStr.charAt(0);
    }
}
