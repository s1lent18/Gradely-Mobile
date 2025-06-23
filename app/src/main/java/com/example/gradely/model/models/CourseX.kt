package com.example.gradely.model.models

import kotlinx.serialization.Serializable

@Serializable
data class CourseX(
    val bestRatedComment: String,
    val id: String,
    val name: String,
    val rating: Double,
    val worstRatedComment: String,
    val numberOfStudents: Int
)