package org.nexivo.kt.specifics.flow

infix fun <T> T?.otherwise (other: T): T  = if (this == null) other else this

inline infix fun <T> T?.otherwise(block: () -> T): T  = if (this == null) block() else this

inline infix fun <T> T?.ifNotSet(block: () -> Unit): Unit { if (this == null) { block() } }

inline infix fun <T> T?.ifSet(block: (T) -> Unit): Unit { if (this != null) { block(this) } }

// todo: this needs a better function name
inline infix fun <T, R> T?.ifValid(block: (T) -> R): R? = if (this != null) { block(this) } else { null }

inline fun <T> T?.ifNotSetTo(new: T, block: () -> Unit): Unit { if (this != null && this != new) { block() }}