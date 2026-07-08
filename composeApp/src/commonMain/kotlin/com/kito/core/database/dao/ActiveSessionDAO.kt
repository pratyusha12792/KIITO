package com.kito.core.database.dao

import androidx.room.Dao
import androidx.room.Upsert
import com.kito.core.database.entity.ActiveSessionEntity

@Dao
interface ActiveSessionDAO {
    @Upsert
    suspend fun insertActiveSession(activeSession: ActiveSessionEntity)
}
