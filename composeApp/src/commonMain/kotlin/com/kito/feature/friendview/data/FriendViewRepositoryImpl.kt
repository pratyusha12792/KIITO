package com.kito.feature.friendview.data

import com.kito.core.database.entity.SectionEntity
import com.kito.core.database.entity.StudentEntity
import com.kito.core.sync.data.ActiveSessionConfig
import com.kito.core.sync.data.StudentElectiveConfig
import com.kito.feature.friendview.data.mapper.toDomain
import com.kito.feature.friendview.domain.model.FriendScheduleItem
import com.kito.feature.friendview.domain.repository.FriendViewRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import org.koin.core.annotation.Provided

class FriendViewRepositoryImpl(
    @Provided private val client: HttpClient,
) : FriendViewRepository {
    override suspend fun getFriendSchedule(roll: String): List<FriendScheduleItem> {
        val student = client.get("rest/v1/students") {
            parameter("roll_no", "eq.$roll")
            parameter("select", "*")
        }.body<List<StudentEntity>>().firstOrNull() ?: return emptyList()

        if (student.section.isBlank()) return emptyList()

        val version = client.get("rest/v1/active_session") {
            parameter("select", "*")
        }.body<List<ActiveSessionConfig>>().firstOrNull()?.version ?: return emptyList()

        val timetable = client.get("rest/v1/timetable") {
            parameter("section", "eq.${student.section}")
            parameter("batch", "eq.${student.batch}")
            parameter("version", "eq.$version")
            parameter("select", "*")
        }.body<List<SectionEntity>>()

        if (student.batch != "batch_3") return timetable.map { it.toDomain() }

        val elective = client.get("rest/v1/student_elective") {
            parameter("roll_no", "eq.$roll")
            parameter("select", "*")
        }.body<List<StudentElectiveConfig>>().firstOrNull() ?: return timetable.map { it.toDomain() }

        val elective1Rows = client.get("rest/v1/timetable") {
            parameter("section", "eq.${elective.elective_1}")
            parameter("batch", "eq.${elective.batch}")
            parameter("version", "eq.$version")
            parameter("select", "*")
        }.body<List<SectionEntity>>()

        val elective2Rows = client.get("rest/v1/timetable") {
            parameter("section", "eq.${elective.elective_2}")
            parameter("batch", "eq.${elective.batch}")
            parameter("version", "eq.$version")
            parameter("select", "*")
        }.body<List<SectionEntity>>()

        return (timetable + elective1Rows + elective2Rows).map { it.toDomain() }
    }
}
