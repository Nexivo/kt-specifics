package org.nexivo.kt.specifics.system

import org.nexivo.kt.specifics.string.otherwise

infix fun String.environmentValue(defaultValue: String): String = System.getenv(this) otherwise defaultValue

fun String.environmentValue(): String = this environmentValue ""

infix fun Array<String>.environmentValue(defaultValue: String): String = this.filter { !it.isNullOrBlank() }.map { System.getenv(it) }.filter { !it.isNullOrBlank() }.firstOrNull() ?: defaultValue

fun Array<String>.environmentValue(): String = this environmentValue ""
