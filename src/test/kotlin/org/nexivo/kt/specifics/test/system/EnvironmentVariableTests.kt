package org.nexivo.kt.specifics.test.system

import com.winterbe.expekt.should
import mockit.Mock
import mockit.MockUp
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.xdescribe
import org.nexivo.kt.specifics.flow.ifNotSet
import org.nexivo.kt.specifics.system.environmentValue

private var mockedSystem: MockUp<System>? = null

class EnvironmentVariableTests: Spek({

    mockedSystem ifNotSet {
        mockedSystem = object : MockUp<System>() {
            @Mock
            fun getenv(key: String): String =
                when (key) {
                    "TEST_VALUE_ONE"     -> "one"
                    "TEST_VALUE_TWO"     -> "two"
                    "TEST_VALUE_1"       -> "1"
                    "TEST_VALUE_EMPTY"   -> ""
                    "TEST_VALUE_BLANK"   -> "   "
                    "TEST_VALUE_2_BLANK" -> "  "
                    "TEST_VALUE_2_EMPTY" -> ""
                    else -> ""
                }
        }
    }

    describe("environment variable dsl behavior") {

        val skip: Boolean = System.getenv().isEmpty()

        if (!skip) {

            given("multiple possible variable names") {

                given("one of the variables has at least one value") {

                    given("a default value is provided") {

                        it("should evaluate to the first non-blank variable") {

                            val actual:   String = arrayOf("TEST_VALUE_1", "TEST_VALUE_ONE") environmentValue "unused default value"
                            val expected: String = "1"

                            actual.should.be.equal(expected)
                        }
                    }

                    given("a default value was not provided") {

                        it("should evaluate to the first non-blank variable") {

                            val actual:   String = arrayOf("TEST_VALUE_1", "TEST_VALUE_ONE").environmentValue()
                            val expected: String = "1"

                            actual.should.be.equal(expected)
                        }
                    }
                }

                given("are all blank or empty") {

                    given("a default value is provided") {

                        it("should evaluate to the default value") {

                            val actual:   String = arrayOf("TEST_VALUE_EMPTY", "TEST_VALUE_BLANK") environmentValue "default value"
                            val expected: String = "default value"

                            actual.should.be.equal(expected)
                        }
                    }

                    given("a default value was not provided") {

                        it("should evaluate to an empty string") {

                            val actual:   String = arrayOf("TEST_VALUE_EMPTY", "TEST_VALUE_BLANK").environmentValue()
                            val expected: String = ""

                            actual.should.be.equal(expected)
                        }
                    }
                }
            }

            given("a single variable name") {

                given("it has a value") {

                    given("a default value is provided") {

                        it("should evaluate to the variable value") {

                            val actual:   String = "TEST_VALUE_TWO" environmentValue "unused default value"
                            val expected: String = "two"

                            actual.should.be.equal(expected)
                        }
                    }

                    given("a default value was not provided") {

                        it("should evaluate to the first non-blank variable") {

                            val actual:   String = "TEST_VALUE_TWO".environmentValue()
                            val expected: String = "two"

                            actual.should.be.equal(expected)
                        }
                    }
                }

                given("it is blank") {

                    given("a default value is provided") {

                        it("should evaluate to the default value") {

                            val actual:   String = "TEST_VALUE_TWO_BLANK" environmentValue "default value"
                            val expected: String = "default value"

                            actual.should.be.equal(expected)
                        }
                    }

                    given("a default value was not provided") {

                        it("should evaluate to an empty string") {

                            val actual:   String = "TEST_VALUE_TWO_BLANK".environmentValue()
                            val expected: String = ""

                            actual.should.be.equal(expected)
                        }
                    }
                }

                given("it is empty") {

                    given("a default value is provided") {

                        it("should evaluate to the default value") {

                            val actual:   String = "TEST_VALUE_TWO_EMPTY" environmentValue "default value"
                            val expected: String = "default value"

                            actual.should.be.equal(expected)
                        }
                    }

                    given("a default value was not provided") {

                        it("should evaluate to an empty string") {

                            val actual:   String = "TEST_VALUE_TWO_EMPTY".environmentValue()
                            val expected: String = ""

                            actual.should.be.equal(expected)
                        }
                    }
                }
            }
        } else {
            xdescribe("skipping environment variables test") {
                it("environment does not support variables") {}
            }
        }
    }
})
