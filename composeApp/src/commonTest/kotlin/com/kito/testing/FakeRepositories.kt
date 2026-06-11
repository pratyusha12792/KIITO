package com.kito.testing

import com.kito.feature.attendance.domain.model.Attendance
import com.kito.feature.attendance.domain.repository.AttendanceRepository
import com.kito.feature.exam.domain.model.ExamSchedule
import com.kito.feature.exam.domain.repository.ExamRepository
import com.kito.feature.faculty.domain.model.Faculty
import com.kito.feature.faculty.domain.model.FacultyScheduleSlot
import com.kito.feature.faculty.domain.repository.FacultyRepository
import com.kito.feature.schedule.domain.model.ScheduleItem
import com.kito.feature.schedule.domain.repository.ScheduleRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class FakeAttendanceRepository(
    initial: List<Attendance> = emptyList(),
) : AttendanceRepository {
    private val flow = MutableStateFlow(initial)
    fun emit(items: List<Attendance>) { flow.value = items }
    override fun observeAttendance(): Flow<List<Attendance>> = flow
    override suspend fun deleteAllAttendance() { flow.value = emptyList() }
}

class FakeExamRepository(
    private val items: List<ExamSchedule> = emptyList(),
) : ExamRepository {
    override suspend fun getExamSchedule(roll: String): List<ExamSchedule> = items
}

class FakeFacultyRepository(
    private val all: List<Faculty> = emptyList(),
    private val schedule: List<FacultyScheduleSlot> = emptyList(),
) : FacultyRepository {
    override suspend fun getAllFaculty(): List<Faculty> = all
    override suspend fun searchFaculty(query: String): List<Faculty> =
        all.filter { it.name.contains(query, ignoreCase = true) }
    override suspend fun getFacultyById(id: Long): Faculty? = all.find { it.id == id }
    override suspend fun getFacultySchedule(id: Long): List<FacultyScheduleSlot> = schedule
}

class FakeScheduleRepository(
    private val items: List<ScheduleItem> = emptyList(),
) : ScheduleRepository {
    override fun getAllSchedule(rollNo: String): Flow<List<ScheduleItem>> =
        MutableStateFlow(items)
    override fun getScheduleForDay(rollNo: String, day: String): Flow<List<ScheduleItem>> =
        MutableStateFlow(items.filter { it.subject.isNotEmpty() })
}
