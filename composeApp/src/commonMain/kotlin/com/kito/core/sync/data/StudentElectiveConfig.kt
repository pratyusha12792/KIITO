package com.kito.core.sync.data

import kotlinx.serialization.Serializable

@Serializable
data class StudentElectiveConfig(
    val roll_no: String,
    val elective_1: String,
    val elective_2: String,
    val batch: String,
)
