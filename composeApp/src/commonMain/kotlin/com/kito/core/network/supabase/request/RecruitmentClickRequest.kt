package com.kito.core.network.supabase.request

import kotlinx.serialization.Serializable

@Serializable
data class RecruitmentClickRequest(
    val roll_number: String
)