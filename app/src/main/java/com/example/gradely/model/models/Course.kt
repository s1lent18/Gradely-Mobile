package com.example.gradely.model.models

import kotlinx.serialization.Serializable

@Serializable
data class Course(
    val courseId: String = "",
    val details: String? = null,
    val gpa: Double = 0.0,
    val section: String = ""
)