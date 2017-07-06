package com.github.leo_scream.java_lab.tdd;

import java.util.Iterator;
import java.util.List;

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
        return null;
    }
}
