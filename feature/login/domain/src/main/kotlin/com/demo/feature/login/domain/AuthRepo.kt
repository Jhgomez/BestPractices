package com.demo.feature.login.domain


interface AuthRepo {

    /**
     * @return Boolean, true if token was created, false otherwise. Note this sensitive values never
     * boil up to the presentation layer
     */
    suspend fun createRequestToken(): Boolean

    /**
     * @return Boolean, true if token was created, false otherwise. Note this sensitive values never
     * boil up to the presentation layer
     */
    suspend fun createGuestSession(): Boolean
}