package com.example.gradely.model.models

data class TeacherDataX(
    val address: String,
    val assignedEmail: String,
    val bloodGroup: String,
    val department: Department,
    val emergency: String,
    val gender: String,
    val personalEmail: String,
    val phone: String,
    val position: String,
    val qualification: List<String>,
    val sections: List<Section>,
    val teacherId: String,
    val teacherName: String,
    val token: String
)