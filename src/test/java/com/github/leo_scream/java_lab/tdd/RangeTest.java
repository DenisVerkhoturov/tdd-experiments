package com.github.leo_scream.java_lab.tdd;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Denis Verkhoturov, mod.satyr@gmail.com
 */
class RangeTest {
    @Test
    @DisplayName("Range lower bound must be less-than or equals to upper bound")
    void range() {
        assertThrows(IllegalArgumentException.class, () -> Range.of(47, 42));
    }

    @Test
    @DisplayName("Range is before another range if it's upper bound is less-than or equals to lower bound of another")
    void isBefore() {
        assertTrue(Range.of(42, 47).isBefore(Range.of(80, 85)));
    }

    @Test
    @DisplayName("Range is after another range if it's lower bound is greater-than or equals to upper bound of another")
    void isAfter() {
        assertTrue(Range.of(42, 47).isAfter(Range.of(17, 40)));
    }

    @Test
    @DisplayName("Ranges are concurrent if they has common values")
    void isConcurrent() {
        assertTrue(Range.of(42, 47).isConcurrent(Range.of(17, 45)));
    }

    @DisplayName("Value of range, if value is in bounds or equals to one of them")
    @ParameterizedTest(name = "{0} ∈ [42; 47]")
    @CsvSource({"42", "43", "44", "45", "46", "47"})
    void contains(final long value) {
        assertTrue(Range.of(42, 47).contains(value));
    }

    @DisplayName("Value not of range, if value is out of bounds of range")
    @ParameterizedTest(name = "{0} ∉ [42; 47]")
    @CsvSource({"40", "41", "48", "49"})
    void notContains(final long value) {
        assertFalse(Range.of(42, 47).contains(value));
    }

    @Test
    @DisplayName("Range iterator throws NoSuchElementException if has not next value")
    void iteratorThrowsNSEE() {
        final Iterator<Long> iterator = Range.of(42, 47).asIterator();
        while (iterator.hasNext()) iterator.next();
        assertThrows(NoSuchElementException.class, iterator::next);
    }
}