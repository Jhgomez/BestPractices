package com.demo.core.data.client.api

sealed interface DataResult<out T>

class Success<R>(val result: R): DataResult<R>

sealed interface Error<R>: DataResult<R> {
    val code: Short
    val message: String
}

class BadRequest<R>(override val code: Short, override val message: String): Error<R>
class Unauthorized<R>(override val code: Short, override val message: String): Error<R>
class InternalServerError<R>(override val code: Short, override val message: String): Error<R>
class UnhandledHttpCode<R>(override val code: Short, override val message: String): Error<R>
class NetworkError<R>(override val message: String): Error<R> {
    override val code: Short = 0
}

