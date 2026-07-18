package com.kito.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class StudentElectiveEntity(
    @PrimaryKey val roll_no: String,
    val elective_1: String,
    val elective_2: String,
    val batch: String,
)
