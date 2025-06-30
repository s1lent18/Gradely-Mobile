package com.example.gradely.model.models

import kotlinx.serialization.Serializable

@Serializable
data class Semester(
    val courses: List<Course>,
    val number: Int,
    val name: String,
    val creditsRegistered: Int,
    val creditsEarned: Int
)