package org.nexivo.kt.specifics.equality

fun Any?.createHashCode(vararg values: Any?): Int {

    if (this == null) return 0

    if (values.isEmpty()) return this.hashCode()

    var result: Int = values[0]?.hashCode() ?: 0

    values.drop(1).forEach {
        when (it) {
            is Iterable<*> -> result = 31 * + it.map { it?.hashCode() ?: 0 }.sumBy { 31 + result + it }

            else -> result = 31 * result + (it?.hashCode() ?: 0)
        }
    }

    return result
}