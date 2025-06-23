package com.example.gradely.model.models

import kotlinx.serialization.Serializable

@Serializable
data class Section(
    val course: List<CourseX>,
    val name: String
)