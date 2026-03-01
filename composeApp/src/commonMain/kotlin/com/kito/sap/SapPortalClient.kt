package com.kito.sap

class SapPortalClient {
    suspend fun fetchAttendance(username: String, password: String, academicYear: String = "", termCode: String = ""): AttendanceResult {
        try {
            val parsedAttendance = AttendanceData()
            return AttendanceResult.Success(parsedAttendance)
        } catch (e: Exception) {
            return AttendanceResult.Error(e.message?:"")
        }
    }
}

sealed class AttendanceResult {
    data class Success(val data: AttendanceData) : AttendanceResult()
    data class Error(val message: String) : AttendanceResult()
}

data class AttendanceData(
    val subjects: List<SubjectAttendance> = emptyList()
)

data class SubjectAttendance(
    val subjectCode: String,
    val subjectName: String,
    val attendedClasses: Int,
    val totalClasses: Int,
    val percentage: Double,
    val facultyName: String = ""
)