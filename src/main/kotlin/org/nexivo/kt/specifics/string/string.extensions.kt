package org.nexivo.kt.specifics.string

infix fun String?.otherwise (defaultValue: String): String = if (this.isNullOrBlank()) defaultValue else this!!

inline infix fun String?.notBlank (block: (String) -> String): String = if (this.isNullOrBlank()) "" else block(this!!)

infix fun Int.pluralize(pluralized: String): String = if ( this != 1 ) pluralized else ""

fun Int.pluralize(singular: String, pluralized: String): String = if ( this != 1 ) pluralized else singular