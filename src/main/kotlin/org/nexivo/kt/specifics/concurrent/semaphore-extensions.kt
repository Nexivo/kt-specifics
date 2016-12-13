package org.nexivo.kt.specifics.concurrent

import java.util.concurrent.Semaphore

infix fun Semaphore.acquire(permits: Int): Semaphore {

    try {
        this.acquire(permits)
    } finally {
        return this
    }
}

infix fun Semaphore.release(permits: Int): Semaphore {

    try {
        this.release(permits)
    } finally {
        return this
    }
}

inline infix fun Semaphore.`safely do`(block: () -> Unit): Semaphore {

    try {
        this.acquire()

        block()

        this.release()
    } finally {
        return this
    }
}

inline infix fun Semaphore.`if not blocked`(block: () -> Unit): Semaphore {

    try {
        if (this.tryAcquire()) {

            block()

            this.release()
        }
    } finally {
        return this
    }
}
