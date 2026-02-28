package com.kito.feature.holiday.presentation

data class Holiday(
    val name: String,
    val date: String,
    val month: String
)

val holidayList2026 = listOf(
    Holiday("Republic Day", "26 Jan, 2026", "January 2026"),
    Holiday("Pongal", "15 Jan, 2026", "January 2026"),
    Holiday("Holi", "4 Mar, 2026", "March 2026"),
    Holiday("International Women's Day", "8 Mar, 2026", "March 2026"),
    Holiday("Gudi Padwa", "18 Mar, 2026", "March 2026"),
    Holiday("Good Friday", "3 Apr, 2026", "April 2026"),
    Holiday("Maha Vishubha Sankranti", "14 Apr, 2026", "April 2026"),
    Holiday("Independence Day", "15 Aug, 2026", "August 2026"),
    Holiday("Janmashtami", "4 Sep, 2026", "September 2026"),
    Holiday("Gandhi Jayanti", "2 Oct, 2026", "October 2026"),
    Holiday("Kali Puja", "7 Nov, 2026", "November 2026"),
    Holiday("Christmas", "25 Dec, 2026", "December 2026")
)
