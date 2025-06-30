package com.example.gradely.model.models

import kotlinx.serialization.Serializable

@Serializable
data class Exam(
    val examScore : String,
    val examTotal : String,
    val weightage : String,
    val breakDowns: List<BreakDowns>
)