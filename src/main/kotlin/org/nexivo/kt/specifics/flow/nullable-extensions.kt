package org.nexivo.kt.specifics.flow

infix fun <T> T?.otherwise (other: T): T  = if (this == null) other else this

inline infix fun <T> T?.otherwise (block: () -> T): T  = if (this == null) block() else this

inline infix fun <T> T?.whenNull (block: () -> Unit): Unit { if (this == null) { block() } }

inline infix fun <T> T?.whenNotNull (block: (T) -> Unit): Unit { if (this != null) { block(this) } }

inline infix fun <T, R> T?.ifNotNull (block: (T) -> R): R? = if (this != null) { block(this) } else { null }
