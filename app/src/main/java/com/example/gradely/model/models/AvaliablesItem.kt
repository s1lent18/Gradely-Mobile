package com.example.gradely.model.models

import kotlinx.serialization.Serializable

@Serializable
data class AvaliablesItem(
    val available: List<Available>,
    val courseCode: String,
    val courseId: String,
    val courseName: String,
    val desc: String,
    val status: String,
    val creditHours: Int
)