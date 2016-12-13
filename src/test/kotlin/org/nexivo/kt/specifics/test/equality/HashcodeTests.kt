package org.nexivo.kt.specifics.test.equality

import com.winterbe.expekt.should
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.nexivo.kt.specifics.equality.createHashCode

class HashcodeTests : Spek({

    describe("\"createHashcode\" dsl behavior") {

        given("a null value") {

            it("should return a zero hashcode") {

                val subject:  String? = null
                val actual:   Int     = subject.createHashCode("Lorem", arrayOf<Int>())
                val expected: Int     = 0

                actual.should.be.equal(expected)
            }
        }

        given("an object without any additional inputs") {

            it("should return the hash value of the object") {

                val subject: TestObject = TestObject()
                val actual:   Int        = subject.createHashCode()
                val expected: Int        = 42

                actual.should.be.equal(expected)
            }
        }

        given("an object with additional inputs") {

            it("should return the calculated hash value of the inputs") {

                val subject: TestObject = TestObject()
                val list:     List<Facade> = listOf(
                        object : Facade { override fun hashCode(): Int = 10 },
                        object : Facade { override fun hashCode(): Int = 20 },
                        object : Facade { override fun hashCode(): Int = 30 }
                )
                val actual:   Int          = subject.createHashCode(PseudoHashCode(24), list, PseudoHashCode(42))
                val expected: Int          = 216267

                actual.should.be.equal(expected)
            }
        }
    }
}) {

    @Suppress("EqualsOrHashCode")
    private class TestObject {

        override fun hashCode(): Int = 42
    }

    @Suppress("EqualsOrHashCode")
    private class PseudoHashCode(val code: Int) {

        override fun hashCode(): Int = code
    }

    interface Facade
}