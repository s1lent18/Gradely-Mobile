package com.example.gradely.model.models

data class StudentData(
    val address: String,
    val assignedEmail: String,
    val batch: String,
    val bloodGroup: String,
    val degree: String,
    val dob: String,
    val emergency: String,
    val fatherName: String,
    val gender: String,
    val personalEmail: String,
    val phone: String,
    val semesters: List<Semester>,
    val studentId: String,
    val studentName: String,
    val token: String,
    val status: String
)