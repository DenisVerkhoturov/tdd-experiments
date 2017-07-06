package com.github.leo_scream.java_lab.tdd;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.LongStream;

import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toList;

/**
 * @author Denis Verkhoturov, mod.satyr@gmail.com
 */
public interface Range {

    long lowerBound();

    long upperBound();

    default boolean isBefore(final Range other) {
        requireNonNull(other);
        return upperBound() < other.lowerBound();
    }

    default boolean isAfter(final Range other) {
        requireNonNull(other);
        return other.upperBound() < lowerBound();
    }

    default boolean isConcurrent(final Range other) {
        requireNonNull(other);
        return contains(other.lowerBound()) || contains(other.upperBound());
    }

    default boolean contains(final long value) {
        return lowerBound() <= value && value <= upperBound();
    }

    default List<Long> asList() {
        return LongStream.rangeClosed(lowerBound(), upperBound())
                         .boxed()
                         .collect(toList());
    }

    default Iterator<Long> asIterator() {
        return new Iterator<Long>() {
            private long current = lowerBound();

            @Override
            public boolean hasNext() { return current <= upperBound(); }

            @Override
            public Long next() {
                if (!hasNext()) throw new NoSuchElementException();
                return current++;
            }
        };
    }

    static Range of(final long lower, final long upper) {
        if (lower > upper)
            throw new IllegalArgumentException("Lower bound of range must be less-than or equal to upper bound");
        return new Range() {
            @Override
            public long lowerBound() { return lower; }

            @Override
            public long upperBound() { return upper; }

            @Override
            public String toString() {
                return String.format("Range [%d, %d]", lowerBound(), upperBound());
            }
        };
    }
}
