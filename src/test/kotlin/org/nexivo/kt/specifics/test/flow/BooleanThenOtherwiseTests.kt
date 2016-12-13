package org.nexivo.kt.specifics.test.flow

import com.winterbe.expekt.should
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.nexivo.kt.specifics.flow.otherwise
import org.nexivo.kt.specifics.flow.then

class BooleanThenOtherwiseTests : Spek({

    val alternativeCode: () -> String = { "alternative flow" }

    describe("\"otherwise\" control flow behavior") {

        given("a Boolean expression that evaluates as true") {

            it("should NOT execute the \"otherwise\" block of code") {
                val truthy: Boolean   = true
                var actual:   String? = null
                val expected: String? = null

                truthy otherwise { actual = alternativeCode() }

                actual.should.be.equal(expected)
            }
        }
        given("a Boolean expression that evaluates as false") {

            it("should execute the \"otherwise\" block of code") {
                val truthy:   Boolean = false
                var actual:   String? = null
                val expected: String  = "alternative flow"

                truthy otherwise { actual = alternativeCode() }

                actual.should.be.equal(expected)
                actual.should.not.be.`null`
            }
        }

        given("a Nullable Boolean expression that evaluates as true") {

            it("should execute the \"otherwise\" block of code") {
                val truthy:   Boolean? = true
                var actual:   String?  = null
                val expected: String?  = null

                truthy otherwise { actual = alternativeCode() }

                actual.should.be.equal(expected)
            }
        }

        given("a Nullable Boolean expression that evaluates as false") {

            it("should execute the \"otherwise\" block of code") {
                val truthy:   Boolean? = false
                var actual:   String?  = null
                val expected: String   = "alternative flow"

                truthy otherwise { actual = alternativeCode() }

                actual.should.be.equal(expected)
                actual.should.not.be.`null`
            }
        }

        given("a Nullable Boolean expression that evaluates as null") {

            it("should execute the \"otherwise\" block of code") {
                val truthy:   Boolean? = null
                var actual:   String?  = null
                val expected: String   = "alternative flow"

                truthy otherwise { actual = alternativeCode() }

                actual.should.be.equal(expected)
                actual.should.not.be.`null`
            }
        }
    }

    describe("\"then\" control flow behavior") {

        given("a Boolean expression that evaluates true") {

            it("should execute the \"then\" block of code") {
                val truthy:   Boolean = true
                var actual:   String? = null
                val expected: String  = "alternative flow"

                truthy then { actual = alternativeCode() }

                actual.should.be.equal(expected)
                actual.should.not.be.`null`
            }
        }

        given("a Boolean expression that evaluates false") {

            it("should not execute the \"then\" block of code") {
                val truthy:   Boolean = false
                var actual:   String? = null
                val expected: String? = null

                truthy then { actual = alternativeCode() }

                actual.should.be.equal(expected)
            }
        }

        given("a Nullable Boolean expression that evaluates true") {

            it("should execute the \"then\" block of code") {
                val truthy:   Boolean? = true
                var actual:   String?  = null
                val expected: String   = "alternative flow"

                truthy then { actual = alternativeCode() }

                actual.should.be.equal(expected)
                actual.should.not.be.`null`
            }
        }

        given("a Nullable Boolean expression that evaluates false") {

            it("should not execute the \"then\" block of code") {
                val truthy:   Boolean? = false
                var actual:   String?  = null
                val expected: String?  = null

                truthy then { actual = alternativeCode() }

                actual.should.be.equal(expected)
            }
        }

        given("a Nullable Boolean expression that evaluates null") {

            it("should not execute the \"then\" block of code") {
                val truthy:   Boolean? = null
                var actual:   String?  = null
                val expected: String?  = null

                truthy then { actual = alternativeCode() }

                actual.should.be.equal(expected)
            }
        }
    }
})
