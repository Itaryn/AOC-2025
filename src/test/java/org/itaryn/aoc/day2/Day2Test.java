package org.itaryn.aoc.day2;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Day2Test {

    @Test
    void sumInvalidProductIds_shouldBeValid_ifSimpleValidId() {
        long expected = 1227775554;

        List<ProductRange> productRanges = List.of(
                new ProductRange(11, 22),
                new ProductRange(95, 115),
                new ProductRange(998, 1012),
                new ProductRange(1188511880, 1188511890),
                new ProductRange(222220, 222224),
                new ProductRange(1698522, 1698528),
                new ProductRange(446443, 446449),
                new ProductRange(38593856, 38593862),
                new ProductRange(565653, 565659),
                new ProductRange(824824821, 824824827),
                new ProductRange(2121212118, 2121212124)
        );

        assertEquals(expected, new Day2().sumInvalidProductIds(productRanges, Day2::isSimpleValidId));
    }

    @Test
    void sumInvalidProductIds_shouldBeValid_ifComplexValidId() {
        long expected = 4174379265L;

        List<ProductRange> productRanges = List.of(
                new ProductRange(11, 22),
                new ProductRange(95, 115),
                new ProductRange(998, 1012),
                new ProductRange(1188511880, 1188511890),
                new ProductRange(222220, 222224),
                new ProductRange(1698522, 1698528),
                new ProductRange(446443, 446449),
                new ProductRange(38593856, 38593862),
                new ProductRange(565653, 565659),
                new ProductRange(824824821, 824824827),
                new ProductRange(2121212118, 2121212124)
        );

        assertEquals(expected, new Day2().sumInvalidProductIds(productRanges, Day2::isComplexValidId));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 12, 1234, 101, 10001, 3443})
    void isSimpleValidId_shouldReturnTrue(int productId) {
        assertTrue(Day2.isSimpleValidId(productId));
    }

    @ParameterizedTest
    @ValueSource(ints = {11, 2222, 4343, 157157, 19631963})
    void isSimpleValidId_shouldReturnFalse(int productId) {
        assertFalse(Day2.isSimpleValidId(productId));
    }

    @Test
    void readInput() throws IOException {
        List<ProductRange> expected = List.of(
                new ProductRange(11, 22),
                new ProductRange(95, 115),
                new ProductRange(998, 1012),
                new ProductRange(1188511880, 1188511890),
                new ProductRange(222220, 222224),
                new ProductRange(1698522, 1698528),
                new ProductRange(446443, 446449),
                new ProductRange(38593856, 38593862),
                new ProductRange(565653, 565659),
                new ProductRange(824824821, 824824827),
                new ProductRange(2121212118, 2121212124)
        );

        List<ProductRange> actual = new Day2().readInput("src/test/resources/day2/example.txt");
        assertEquals(11, actual.size());
        assertLinesMatch(
                expected.stream().map(ProductRange::toString).toList(),
                actual.stream().map(ProductRange::toString).toList());
    }
}