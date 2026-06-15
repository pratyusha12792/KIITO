package com.kito.core.auth.domain.repository

import kotlinx.coroutines.flow.Flow

interface CredentialsRepository {
    val isLoggedIn: Flow<Boolean>
    suspend fun getSapPassword(): String
    suspend fun saveSapPassword(password: String): Boolean
    suspend fun clearSapPassword(): Boolean
}
