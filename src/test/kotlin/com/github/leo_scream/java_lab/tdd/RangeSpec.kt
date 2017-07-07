package com.github.leo_scream.java_lab.tdd

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

/**
 * @author Denis Verkhoturov, mod.satyr@gmail.com
 */
class RangeSpec : Spek({
    describe("a range") {
        on("instantiation") {
            it("should throw IllegalArgumentException if lower bound greater than upper") {
                assertFailsWith<IllegalArgumentException> { Range.of(47, 42) }
            }
        }
        on("is before") {
            it("another range if it's upper bound is less-than or equals to lower bound of another") {
                assertTrue { Range.of(42, 47).isBefore(Range.of(80, 85)) }
            }
        }
        on("is after") {
            it("another range if it's lower bound is greater-than or equals to upper bound of another") {
                assertTrue { Range.of(42, 47).isConcurrent(Range.of(17, 45)) }
            }
        }
        on("is concurrent") {
            it("if it has common values") {
                assertTrue { Range.of(42, 47).isConcurrent(Range.of(17, 45)) }
            }
        }
        on("as list") {
            it("should contains every single value") {
                assertEquals(Range.of(42, 47).asList(), listOf(42L, 43L, 44L, 45L, 46L, 47L))
            }
        }
        on("iterator") {
            it("should throw NoSuchElementException if has not next value") {
                val iterator = Range.of(42, 47).asIterator()
                while (iterator.hasNext()) iterator.next()
                assertFailsWith<NoSuchElementException> { iterator.next() }
            }
        }
    }
})
