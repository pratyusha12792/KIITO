package com.kito.core.sync.data

import kotlinx.serialization.Serializable

@Serializable
data class ActiveSessionConfig(
    val id: Int = 1,
    val academic_year: String,
    val term_code: String,
    val version: Int
)
