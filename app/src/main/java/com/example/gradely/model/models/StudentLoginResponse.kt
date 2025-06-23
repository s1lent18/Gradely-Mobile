package com.example.gradely.model.models

import kotlinx.serialization.Serializable

@Serializable
data class StudentLoginResponse(
    val studentData: StudentData
)