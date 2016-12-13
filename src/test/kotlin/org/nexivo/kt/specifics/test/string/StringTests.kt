package org.nexivo.kt.specifics.test.string

import com.winterbe.expekt.should
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.nexivo.kt.specifics.string.notBlank
import org.nexivo.kt.specifics.string.otherwise
import org.nexivo.kt.specifics.string.pluralize

class StringTests : Spek({

    describe("\"String\" dsl behavior") {

        given("a string value that is not null or blank") {

            val subject: String = "this string is neither null nor blank"

            it("should result in the original value") {

                val actual:   String = subject otherwise "not this value"
                val expected: String = "this string is neither null nor blank"

                actual.should.be.equal(expected)
            }

            it ("should result in calling the block of code") {

                val actual:   String = subject notBlank String::toUpperCase
                val expected: String = "THIS STRING IS NEITHER NULL NOR BLANK"

                actual.should.be.equal(expected)
            }
        }

        given("a string value that is blank") {

            val subject: String = "  "

            it("should result in the default value") {

                val actual:   String = subject otherwise "i'm expected to arrive at the show"
                val expected: String = "i'm expected to arrive at the show"

                actual.should.be.equal(expected)
            }

            it ("should result in skipping the block of code and return an empty string") {

                val actual:   String = subject notBlank String::toUpperCase
                val expected: String = ""

                actual.should.be.equal(expected)
            }
        }

        given("a string value that is null") {

            val subject: String? = null

            it("should result in the default value") {

                val actual:   String  = subject otherwise "i'm expected to arrive at the show"
                val expected: String  = "i'm expected to arrive at the show"

                actual.should.be.equal(expected)
            }

            it("should result in skipping the block of code and return an empty string") {

                val actual:   String = subject notBlank String::toUpperCase
                val expected: String = ""

                actual.should.be.equal(expected)
            }
        }

        given("a value of zero") {

            val subject: Int = 0

            it("should return the pluralized value") {

                val actual:   String = subject pluralize "s"
                val expected: String = "s"

                actual.should.be.equal(expected)
            }

            it("should return the pluralized value, not the singular value") {

                val actual:   String = subject.pluralize("s", "s'")
                val expected: String = "s'"

                actual.should.be.equal(expected)
            }
        }

        given("a value greater than one") {

            val subject: Int = 2

            it("should return the pluralized value") {

                val actual:   String = subject pluralize "s"
                val expected: String = "s"

                actual.should.be.equal(expected)
            }

            it("should return the pluralized value, not the singular value") {

                val actual:   String = subject.pluralize("s", "s'")
                val expected: String = "s'"

                actual.should.be.equal(expected)
            }
        }

        given("a value of one") {

            val subject: Int = 1

            it("should return an empty string") {

                val actual:   String = subject pluralize "s"
                val expected: String = ""

                actual.should.be.equal(expected)
            }

            it("should return the singular value, not the pluralized value") {

                val actual:   String = subject.pluralize("s", "s'")
                val expected: String = "s"

                actual.should.be.equal(expected)
            }
        }
    }
})