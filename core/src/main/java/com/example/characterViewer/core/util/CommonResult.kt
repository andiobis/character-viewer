package com.example.characterViewer.core.util

sealed class CommonResult<out V : Any, out E : Error> {

    open operator fun component1() : V? = null
    open operator fun component2() : E? = null

    inline fun <X> fold(success: (V) -> X, failure: (E) -> X): X = when (this) {
        is Success -> success(this.value)
        is Failure -> failure(this.error)
    }

    abstract fun get(): V

    class Success<out V : Any>(val value: V) : CommonResult<V, Nothing>() {
        override fun component1(): V = value

        override fun get(): V = value

        override fun toString() = "[Success: $value]"

        override fun hashCode(): Int = value.hashCode()

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            return other is Success<*> && value == other.value
        }
    }

    class Failure<out E : Error>(val error: E) : CommonResult<Nothing, E>() {

        override fun get() = throw IllegalAccessException(error.message)

        override fun component2(): E = error

        override fun toString() = "[Failure: $error]"

        override fun hashCode(): Int = error.hashCode()

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            return other is Failure<*> && error == other.error
        }
    }

    companion object {
        fun <E : Error> error(ex: E) =
            Failure(ex)

        fun <V : Any> success(v: V) = Success(v)
    }
}

fun <V : Any> CommonResult<V, *>.success(f: (V) -> Unit) = fold(f) {}

fun <E : Error> CommonResult<*, E>.failure(f: (E) -> Unit) = fold({}, f)