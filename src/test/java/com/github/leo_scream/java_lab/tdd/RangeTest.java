package com.github.leo_scream.java_lab.tdd;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Denis Verkhoturov, mod.satyr@gmail.com
 */
@DisplayName("Range junit")
class RangeTest {
    @Test
    @DisplayName("Range lower bound must be less-than or equals to upper bound")
    void range() {
        assertThrows(IllegalArgumentException.class, () -> Range.of(47, 42));
    }

    @Test
    @DisplayName("Range is before another range if it's upper bound is less-than or equals to lower bound of another")
    void isBefore() {
        assertThat(Range.of(42, 47).isBefore(Range.of(80, 85)), is(true));
    }

    @Test
    @DisplayName("Range is after another range if it's lower bound is greater-than or equals to upper bound of another")
    void isAfter() {
        assertThat(Range.of(42, 47).isAfter(Range.of(17, 40)), is(true));
    }

    @Test
    @DisplayName("Ranges are concurrent if has common values")
    void isConcurrent() {
        assertThat(Range.of(42, 47).isConcurrent(Range.of(17, 45)), is(true));
    }

    @ParameterizedTest(name = "{0} ∈ [42; 47]")
    @CsvSource({"42", "43", "44", "45", "46", "47"})
    @DisplayName("Values in bounds belongs to a range")
    void contains(final long value) {
        assertThat(Range.of(42, 47).contains(value), is(true));
    }

    @ParameterizedTest(name = "{0} ∉ [42; 47]")
    @CsvSource({"40", "41", "48", "49"})
    @DisplayName("Values out of bounds do not belongs to a range")
    void notContains(final long value) {
        assertThat(Range.of(42, 47).contains(value), is(false));
    }

    @Test
    @DisplayName("Range as list contains all the values")
    void asList() {
        assertThat(Range.of(42, 47).asList(), is(Arrays.asList(42L, 43L, 44L, 45L, 46L, 47L)));
    }

    @Test
    @DisplayName("Range iterator throws NoSuchElementException if has not next value")
    void iteratorThrowsNSEE() {
        final Iterator<Long> iterator = Range.of(42, 47).asIterator();
        while (iterator.hasNext()) iterator.next();
        assertThrows(NoSuchElementException.class, iterator::next);
    }
}