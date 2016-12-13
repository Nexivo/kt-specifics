package org.nexivo.kt.specifics.infixit

inline infix fun <T> T.apply(block: T.() -> Unit): T {

    block()

    return this
}
