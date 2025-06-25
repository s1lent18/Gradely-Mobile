package com.example.gradely.model.dataRequests

data class StudentRegistrationRequest(
    val courseId : String,
    val sectionId : String,
    val teacherId : String
)
