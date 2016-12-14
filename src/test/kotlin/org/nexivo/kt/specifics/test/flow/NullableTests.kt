package org.nexivo.kt.specifics.test.flow

import com.winterbe.expekt.should
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.nexivo.kt.specifics.flow.ifNotSet
import org.nexivo.kt.specifics.flow.ifSet
import org.nexivo.kt.specifics.flow.ifValid
import org.nexivo.kt.specifics.flow.otherwise

class NullableTests: Spek({

    fun <T> echoCode(value: T): T = value

    describe("\"otherwise\" control flow behavior") {

        given("a null value") {

            it("should evaluate the \"otherwise\" block of code") {

                val subject:  Int? = null
                val actual:   Int  = subject otherwise { echoCode(20 + 2 + 16 + 4) }
                val expected: Int  = 42

                actual.should.be.equal(expected)
                actual.should.not.be.`null`
            }

            it("should evaluate the \"otherwise\" value") {

                val subject:  Int? = null
                val actual:   Int  = subject otherwise 42
                val expected: Int  = 42

                actual.should.be.equal(expected)
                actual.should.not.be.`null`
            }
        }

        given("a non-null nullable value") {

            it("should NOT evaluate the \"otherwise\" block of code") {

                val subject:  Int? = 42
                val actual:   Int  = subject otherwise { echoCode(-22 - 18 - 2) }
                val expected: Int  = 42

                actual.should.be.equal(expected)
                actual.should.not.be.equal(-42)
            }

            it("should NOT evaluate the \"otherwise\" value") {

                val subject:  Int? = 42
                val actual:   Int  = subject otherwise -42
                val expected: Int  = 42

                actual.should.be.equal(expected)
                actual.should.not.be.equal(-42)
            }
        }
    }

    describe("\"ifNotSet\" control flow behavior") {

        given("a null value") {

            it("should evaluate the \"ifNotSet\" block of code") {

                val subject: String?  = null
                var actual:  Boolean? = null

                subject?.capitalize() ifNotSet { actual = echoCode(true) }

                actual.should.be.`true`
                actual.should.not.be.`null`
            }
        }

        given("a non-null nullable value") {

            it("should NOT evaluate the \"ifNotSet\" block of code") {

                val subject: String?  = "Pha Pha Fooey!"
                var actual:  Boolean? = null

                subject?.capitalize() ifNotSet { actual = echoCode(false) }

                actual.should.be.`null`
                actual.should.not.be.`false`
            }
        }
    }
    
    describe("\"ifSet\" control flow behavior") {

        given("a null value") {

            it("should NOT evaluate the \"ifSet\" block of code") {

                val subject: String?  = null
                var actual:  Boolean? = null

                subject?.capitalize() ifSet {
                    actual = false
                }

                actual.should.be.`null`
                actual.should.not.be.`false`
            }
        }

        given("a non-null nullable value") {

            it("should evaluate the \"ifSet\" block of code") {

                val subject: String?  = "Pha Pha Fooey!"
                var actual:  Boolean? = null

                subject?.capitalize() ifSet {
                    actual = true
                }

                actual.should.be.`true`
                actual.should.not.be.`null`
            }
        }
    }

    describe("\"otherwise\" control flow behavior") {

        given("a null value") {

            it("should evaluate the \"ifValid\" expression") {

                val subject: String?  = null
                val actual:  Boolean? = subject?.capitalize() ifValid { echoCode(true) }

                actual.should.be.`null`
                actual.should.not.be.`true`
            }
        }

        given("a non-null nullable value") {

            it("should evaluate the \"ifValid\" expression") {

                val subject: String? = "Pha Pha Fooey!"
                val actual:  Int?    = subject ifValid { echoCode(42) }
                val expected: Int    = 42

                actual.should.be.equal(expected)
                actual.should.not.be.`null`
            }
        }
    }
})
