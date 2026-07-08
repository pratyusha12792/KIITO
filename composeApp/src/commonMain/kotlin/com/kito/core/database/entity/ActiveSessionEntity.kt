package com.kito.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ActiveSessionEntity(
    @PrimaryKey
    val id: Int = 1,
    val academic_year: String,
    val term_code: String,
    val version: Int
)
