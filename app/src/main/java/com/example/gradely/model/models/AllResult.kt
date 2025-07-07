package com.example.gradely.model.models

data class AllResult(
    val assignments: List<Assignment>,
    val classParticipationScore: String,
    val classParticipationTotal: String,
    val courseCode: String,
    val finalExam : Exam,
    val mid1 : Exam,
    val mid2 : Exam,
    val name: String,
    val projectScore: String,
    val projectTotal: String,
    val quizzes: List<Quiz>,
    val studentId: String,
    val creditCount: Int
)