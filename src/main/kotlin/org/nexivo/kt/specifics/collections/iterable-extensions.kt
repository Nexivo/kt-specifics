package org.nexivo.kt.specifics.collections

inline infix fun <T> Iterable<T>.forEach(block: (T) -> Unit): Unit {

    this.iterator() forEach block
}

inline infix fun <T> Iterator<T>.forEach(block: (T) -> Unit): Unit {

    while (this.hasNext()) {
        block(this.next())
    }
}
