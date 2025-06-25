package com.example.gradely.model.models

data class Details(
    val assignments: List<Any>,
    val attendance: List<Any>,
    val classParticipationScore: String,
    val classParticipationTotal: String,
    val courseCode: String,
    val finalExamScore: String,
    val finalExamTotal: String,
    val mid1Score: String,
    val mid1Total: String,
    val mid2Score: String,
    val mid2Total: String,
    val name: String,
    val projectScore: String,
    val projectTotal: String,
    val quizzes: List<Any>
)