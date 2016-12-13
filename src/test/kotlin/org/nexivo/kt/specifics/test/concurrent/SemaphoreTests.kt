package org.nexivo.kt.specifics.test.concurrent

import com.winterbe.expekt.should
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.nexivo.kt.specifics.concurrent.`if not blocked`
import org.nexivo.kt.specifics.concurrent.`safely do`
import org.nexivo.kt.specifics.concurrent.acquire
import org.nexivo.kt.specifics.concurrent.release
import java.util.concurrent.Semaphore

class SemaphoreTests: Spek({

    describe("\"acquire\" dsl behavior") {

        given("a Semaphore") {

            on ("calling the \"acquire\" dsl method") {

                it("should acquire a semaphore lock") {

                    val subject: Semaphore = Semaphore(3) acquire 1
                    val expected: Int       = 2

                    subject.availablePermits().should.be.equal(expected)

                    subject.release()
                }
            }

            on ("calling the \"release\" dsl method") {

                it("should release a semaphore lock") {

                    val subject: Semaphore = Semaphore(4) acquire 3
                    val expected: Int       = 3

                    subject.availablePermits().should.be.equal(1)

                    subject release 2

                    subject.availablePermits().should.be.equal(expected)

                    subject.release()
                }
            }

            on ("calling the \"safely do\" dsl method") {

                it("should only do block ") {

                    val subject: Semaphore = Semaphore(1) acquire 1
                    val expected: Boolean   = false
                    var actual:   Boolean   = false

                    val thread: Thread = Thread {
                        subject `safely do` {
                            actual = true
                        }
                    }

                    thread.start()

                    actual.should.be.equal(expected)

                    subject.release()

                    thread.join()

                    actual.should.be.`true`
                }
            }

            on ("calling the \"if not blocked\" dsl method") {

                given("a blocked \"Semaphore\"") {

                    it("should skip the block ") {

                        val subject: Semaphore = Semaphore(1) acquire 1
                        val expected: Boolean   = false
                        var actual:   Boolean   = false

                        subject `if not blocked` {
                            actual = true
                        }

                        actual.should.be.equal(expected)

                        subject.release()

                        actual.should.be.equal(expected)
                    }
                }

                given("a non-blocked \"Semaphore\"") {

                    it("should not skip the block ") {

                        val subject: Semaphore = Semaphore(1)
                        var actual:   Boolean   = false
                        val expected: Boolean   = true

                        subject `if not blocked` {
                            actual = true
                        }

                        actual.should.be.equal(expected)
                    }
                }
            }
        }
    }
})
