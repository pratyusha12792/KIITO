package com.kito.core.auth.data

import com.kito.core.auth.domain.repository.CredentialsRepository
import com.kito.core.platform.SecureStorage
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Provided

class CredentialsRepositoryImpl(
    @Provided private val secureStorage: SecureStorage,
) : CredentialsRepository {
    override val isLoggedIn: Flow<Boolean> = secureStorage.isLoggedInFlow
    override suspend fun getSapPassword(): String = secureStorage.getSapPassword()
    override suspend fun saveSapPassword(password: String): Boolean = secureStorage.saveSapPassword(password)
    override suspend fun clearSapPassword(): Boolean = secureStorage.clearSapPassword()
}
