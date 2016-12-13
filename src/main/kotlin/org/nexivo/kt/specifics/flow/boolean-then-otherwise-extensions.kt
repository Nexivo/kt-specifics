package org.nexivo.kt.specifics.flow

inline infix fun Boolean.then (block: () -> Unit): Unit {

    if (this) {
        block()
    }
}

inline infix fun Boolean.otherwise (block: () -> Unit): Unit {

    if (!this) {
        block()
    }
}

inline infix fun Boolean?.then (block: () -> Unit): Unit {

    if (this ?: false) {
        block()
    }
}

inline infix fun Boolean?.otherwise (block: () -> Unit): Unit {

    if (!(this ?: false)) {
        block()
    }
}
