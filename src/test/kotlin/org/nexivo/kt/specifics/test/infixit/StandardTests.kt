package org.nexivo.kt.specifics.test.infixit

import com.winterbe.expekt.should
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.nexivo.kt.specifics.infixit.apply

class StandardTests: Spek({

    describe("infix version of \"apply\" behavior") {

        it("should \"apply\" lambda block to the subject") {

            val actual: TestClass = TestClass() apply {
                this.Value = 42
            }

            actual.Value.should.be.equal(42)
        }

        it("should resolve to the subject of the \"apply\"") {

            val subject: TestClass      = TestClass()
            val actual:  PseudoContract = subject apply {
                this.Value = 42
            }

            actual.should.be.the.same.equal(subject)
            actual.should.be.instanceof(TestClass::class.java)
            actual.should.be.instanceof(PseudoContract::class.java)
        }
    }
}) {

    interface PseudoContract

    private class TestClass: PseudoContract {
        var Value: Int = -1
    }
}
