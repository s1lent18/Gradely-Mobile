package com.example.gradely.model.models

import kotlinx.serialization.Serializable

@Serializable
data class Semester(
    val courses: List<Course> = emptyList(),
    val gpa: List<Double> = emptyList(),
    val number: Int = 0,
    val sections: List<String> = emptyList()
)