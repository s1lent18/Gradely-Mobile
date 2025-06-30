package com.example.gradely.model.dataRequests

data class StudentRegistrationRequest(
    val semester : String,
    val parts : List<StudentRegistrationPart>
)