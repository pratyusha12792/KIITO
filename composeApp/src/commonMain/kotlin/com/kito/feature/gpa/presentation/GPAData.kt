package com.kito.feature.gpa.presentation
data class Subject(
    val name: String,
    val credit: Int
)

data class SemesterData(
    val semester: Int,
    val branch: String,
    val subjects: List<Subject>
)

val gpaDatabase = listOf(
    SemesterData(
        semester = 1,
        branch = "CSE",
        subjects = listOf(
            Subject("CHEMISTRY", 3),
            Subject("DE & LA", 4),
            Subject("BEE", 2),
            Subject("BETC", 2),
            Subject("ENGLISH", 2),
            Subject("HASS ELECTIVE", 2),
            Subject("CHEMISTRY LAB", 1),
            Subject("WORKSHOP", 1),
            Subject("ENGINEERING LAB", 1),
            Subject("COMMUNICATION LAB", 1),
            Subject("YOGA", 1)
        )
    ),
    SemesterData(
        semester = 2,
        branch = "CSE",
        subjects = listOf(
            Subject("PHYSICS", 3),
            Subject("TC & NM", 4),
            Subject("SCIENCE OF LIVING SYSTEMS", 2),
            Subject("EVS", 2),
            Subject("ENGINEERING ELECTIVE", 2),
            Subject("SCIENCE ELECTIVE", 2),
            Subject("PROGRAMMING LAB", 4),
            Subject("PHYSICS LAB", 1),
            Subject("ENGINEERING DRAWING", 1)
        )
    ),
    SemesterData(
        semester = 3,
        branch = "CSE",
        subjects = listOf(
            Subject("DS", 4),
            Subject("IND 4.0", 2),
            Subject("DSD", 3),
            Subject("AFL", 4),
            Subject("PS", 4),
            Subject("STW", 2),
            Subject("DS LAB", 1),
            Subject("DSD LAB", 1)
        )
    ),
    SemesterData(
        semester = 4,
        branch = "CSE",
        subjects = listOf(
            Subject("OOPJ", 3),
            Subject("OS", 3),
            Subject("COA", 4),
            Subject("DBMS", 3),
            Subject("DM", 4),
            Subject("HASS ELECTIVE", 3),
            Subject("OOPJ LAB", 1),
            Subject("OS LAB", 1),
            Subject("DBMS LAB", 1),
            Subject("VOCATIONAL ELECTIVE", 1)
        )
    ),
    SemesterData(
        semester = 5,
        branch = "CSE",
        subjects = listOf(
            Subject("CN", 3),
            Subject("DAA", 3),
            Subject("SE", 4),
            Subject("PROFESSIONAL ELECTIVE I", 3),
            Subject("PROFESSIONAL ELECTIVE II", 3),
            Subject("HASS ELECTIVE", 3),
            Subject("CN LAB", 1),
            Subject("DAA LAB", 1),
            Subject("K-EXPLORE ELECTIVE", 1)
        )
    ),
    SemesterData(
        semester = 6,
        branch = "CSE",
        subjects = listOf(
            Subject("UHV", 3),
            Subject("AI", 3),
            Subject("ML", 4),
            Subject("CC|SPM|NLP|CV", 3),
            Subject("OPEN ELECTIVE", 3),
            Subject("HASS ELECTIVE", 3),
            Subject("AI LAB", 1),
            Subject("AD LAB", 2),
            Subject("MINI PROJECT", 2)
        )
    ),
    SemesterData(
        semester = 7,
        branch = "CSE",
        subjects = listOf(
            Subject("PROJECT", 5),
            Subject("INTERNSHIP", 2),
            Subject("PROFESSIONAL ELECTIVE", 3),
            Subject("OPEN ELECTIVE", 3),
            Subject("ENGINEERING PROFESSIONAL ELECTIVE", 2),
            Subject("COMPUTING LAB", 2)
        )
    ),
    SemesterData(
        semester = 8,
        branch = "CSE",
        subjects = listOf(
            Subject("PROJECT", 9),
            Subject("MINOR", 3),
            Subject("PROFESSIONAL ELECTIVE", 3),
            Subject("OPEN ELECTIVE", 3)
        )
    )
)