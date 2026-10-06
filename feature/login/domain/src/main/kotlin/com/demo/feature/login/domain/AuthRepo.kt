package com.demo.feature.login.domain

import com.demo.core.domain.common.model.Page

interface AuthRepo {

    /**
     * @return Boolean, true if user was authenticated, false otherwise
     */
    suspend fun login(): Boolean
}