package com.example.gradely.model.models

import kotlinx.serialization.Serializable

@Serializable
data class TeacherLoginResponse(
    val teacherData: TeacherData
)