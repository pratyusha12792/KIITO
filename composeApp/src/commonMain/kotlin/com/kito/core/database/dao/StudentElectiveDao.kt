package com.kito.core.database.dao

import androidx.room.Dao
import androidx.room.Upsert
import com.kito.core.database.entity.StudentElectiveEntity
import org.koin.core.annotation.Provided

@Provided
@Dao
interface StudentElectiveDao {
    @Upsert
    suspend fun upsertStudentElective(entity: StudentElectiveEntity)
}
