package com.example.gradely.model.models

import kotlinx.serialization.Serializable

@Serializable
data class StudentData(
    val address: String = "",
    val assignedEmail: String = "",
    val batch: String = "",
    val bloodGroup: String = "",
    val creditsAttempted: Int = 0,
    val creditsEarned: Int = 0,
    val cgpa: Double = 0.0,
    val degree: String = "",
    val dob: String = "",
    val emergency: String = "",
    val fatherName: String = "",
    val gender: String = "",
    val personalEmail: String = "",
    val phone: String = "",
    val semesters: List<Semester> = emptyList(),
    val studentId: String = "",
    val studentName: String = "",
    val token: String = "",
    val status: String = "",
    val warningCount: Int = 0,
    val fcmToken : String = ""
)