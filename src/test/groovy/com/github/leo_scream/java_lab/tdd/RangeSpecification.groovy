package com.github.leo_scream.java_lab.tdd

import spock.lang.Specification

/**
 * @author Denis Verkhoturov, mod.satyr@gmail.com
 */
class RangeSpecification extends Specification {
    def "Range lower bound must be less-than or equals to upper bound"() {
        when:
        Range.of(47, 42)
        
        then:
        thrown(IllegalArgumentException)
    }

    def "Range is before another range if it's upper bound is less-than or equals to lower bound of another"() {
        expect:
        Range.of(42, 47).isBefore(Range.of(80, 85))
    }

    def "Range is after another range if it's lower bound is greater-than or equals to upper bound of another"() {
        expect:
        Range.of(42, 47).isAfter(Range.of(17, 40))
    }

    def "Ranges are concurrent if has common values"() {
        expect:
        Range.of(42, 47).isConcurrent(Range.of(17, 45))
    }

    def "Values in bounds belongs to a range"() {
        expect:
        Range.of(42, 47).contains(value)
        
        where:
        value << [42, 43, 44, 45, 46, 47]
    }

    def "Values out of bounds do not belongs to a range"() {
        expect:
        !Range.of(42, 47).contains(value)

        where:
        value << [40, 41, 48, 49]
    }

    def "Range as list contains all the values"() {
        expect:
        Range.of(42, 47).asList() == [42, 43, 44, 45, 46, 47] as List<Long>
    }

    def "Range iterator throws NoSuchElementException if has not next value"() {
        given:
        final Iterator<Long> iterator = Range.of(42, 47).asIterator()
        while (iterator.hasNext()) iterator.next()

        when:
        iterator.next()

        then:
        thrown(NoSuchElementException)
    }
}
