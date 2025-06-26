package com.example.gradely.model.models

import kotlinx.serialization.Serializable

@Serializable
data class Quiz(
    val quizScore : String,
    val quizTotal : String
)