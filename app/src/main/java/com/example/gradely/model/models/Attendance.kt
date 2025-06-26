package com.example.gradely.model.models

import kotlinx.serialization.Serializable

@Serializable
data class Attendance(
    val date : String,
    val status : String
)