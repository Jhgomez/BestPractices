package com.demo.core.data.client.api

sealed interface DataResult<out T>

class Success<R>(val result: R): DataResult<R>

sealed interface Error<R>: DataResult<R> {
    val code: Short
    val message: String
}

class BadRequest<R>(override val code: Short, override val message: String): Error<R>
class Unauthorized(override val code: Short, override val message: String): Error
class InternalServerError(override val code: Short, override val message: String): Error
class UnhandledHttpCode(override val code: Short, override val message: String): Error
class NetworkError(override val message: String): Error {
    override val code: Short = 0
}

