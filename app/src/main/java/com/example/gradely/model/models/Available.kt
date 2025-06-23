package com.example.gradely.model.models

import kotlinx.serialization.Serializable

@Serializable
data class Available(
    val sectionId: String,
    val sectionName: String,
    val teacherId: String,
    val teacherName: String
)