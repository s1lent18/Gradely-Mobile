package com.example.gradely.model.models

import kotlinx.serialization.Serializable

@Serializable
data class Details(
    val assignments: List<Assignment>,
    val classParticipationScore: String,
    val classParticipationTotal: String,
    val courseCode: String,
    val mid1 : Exam,
    val mid2 : Exam,
    val finalExam : Exam,
    val name: String,
    val projectScore: String,
    val projectTotal: String,
    val quizzes: List<Quiz>,
)