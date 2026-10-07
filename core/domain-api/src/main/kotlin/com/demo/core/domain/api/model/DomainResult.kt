package com.demo.core.domain.api.model

sealed interface DomainResult<out T>

class DomainSuccess<R>(val result: R): DomainResult<R>

sealed interface DomainError<R>: DomainResult<R> {
    val code: Short
    val message: String
}

class DomainBadRequest<R>(override val code: Short, override val message: String): DomainError<R>
class DomainUnauthorized<R>(override val code: Short, override val message: String): DomainError<R>
class DomainInternalServerError<R>(override val code: Short, override val message: String): DomainError<R>
class DomainUnhandledHttpCode<R>(override val code: Short, override val message: String): DomainError<R>
class DomainNetworkError<R>(override val message: String): DomainError<R> {
    override val code: Short = 0
}
