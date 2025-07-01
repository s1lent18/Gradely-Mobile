package com.example.gradely.model.models

data class CourseAttendance(
    val attendances: List<Attendance>,
    val courseCode: String,
    val courseId: String,
    val courseName: String,
    val sectionId: String,
    val sectionName: String
)