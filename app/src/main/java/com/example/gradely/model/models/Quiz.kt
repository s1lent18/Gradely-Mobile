package com.example.gradely.model.models

import kotlinx.serialization.Serializable

@Serializable
data class Quiz(
    val weightage : String,
    val quizScore : String,
    val quizTotal : String
)