package org.nexivo.kt.specifics.test.exceptions

import com.winterbe.expekt.should
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.nexivo.kt.specifics.exceptions.`parse message`

class ThrowableTests : Spek({

    describe("\"Throwable\" dsl behavior") {

        on("calling \"parse message\" on a \"Throwable\"") {

            given("a null \"Throwable\"") {

                it("should result in a null value") {

                    val subject: Throwable? = null
                    val actual:  String?    = subject `parse message` {
                        exception, message ->

                        "[$exception] \"$message\""
                    }

                    actual.should.be.`null`
                }
            }

            given("a non-null \"Throwable\n") {

                val subject: Throwable  = TestException("Lorem ipsum dolor sit amet, consectetur adipiscing elit.")
                val actual:  String?    = subject `parse message` {
                    exception, message ->

                    "[$exception] \"$message\""
                }
                val expected: String    = "[TestException] \"Lorem ipsum dolor sit amet, consectetur adipiscing elit.\""

                actual.should.be.equal(expected)
            }
        }
    }
}) {

    private class TestException(message: String): Throwable(message)
}