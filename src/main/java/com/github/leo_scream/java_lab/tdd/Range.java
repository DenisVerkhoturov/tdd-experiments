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
    boolean isBefore(final Range other);

    boolean isAfter(final Range other);

    boolean isConcurrent(final Range other);

    long lowerBound();

    long upperBound();

    boolean contains(final long value);

    List<Long> asList();

    Iterator<Long> asIterator();

    static Range of(final long lower, final long upper) {
        if (lower > upper)
            throw new IllegalArgumentException("Lower bound of range must be smaller or equal than upper bound");
        return new Range() {
            @Override
            public boolean isBefore(final Range other) {
                requireNonNull(other);
                return upperBound() < other.lowerBound();
            }

            @Override
            public boolean isAfter(final Range other) {
                requireNonNull(other);
                return other.upperBound() < lowerBound();
            }

            @Override
            public boolean isConcurrent(final Range other) {
                requireNonNull(other);
                return contains(other.lowerBound()) || contains(other.upperBound());
            }

            @Override
            public long lowerBound() { return lower; }

            @Override
            public long upperBound() { return upper; }

            @Override
            public boolean contains(final long value) { return lowerBound() <= value && value <= upperBound(); }

            @Override
            public List<Long> asList() {
                return LongStream.rangeClosed(lowerBound(), upperBound())
                                 .boxed()
                                 .collect(toList());
            }

            @Override
            public Iterator<Long> asIterator() {
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

            @Override
            public String toString() {
                return String.format("Range [%d; %d]", lowerBound(), upperBound());
            }
        };
    }
}
