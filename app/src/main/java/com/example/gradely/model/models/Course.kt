package com.example.gradely.model.models

import kotlinx.serialization.Serializable

@Serializable
data class Course(
    val courseId: String = "",
    val details: Details? = null,
    val gpa: Double = 0.0,
    val section: String = "",
    val grade: String = "",
    val savePoints : List<String> = emptyList(),
    val attendance: List<Attendance> = emptyList()
)