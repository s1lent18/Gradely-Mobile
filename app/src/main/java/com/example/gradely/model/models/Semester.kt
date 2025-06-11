package com.example.gradely.model.models

data class Semester(
    val courses: List<Course>,
    val gpa: List<Double>,
    val number: Int,
    val sections: List<String>
)