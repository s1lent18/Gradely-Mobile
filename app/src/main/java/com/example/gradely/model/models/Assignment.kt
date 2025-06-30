package com.example.gradely.model.models

import kotlinx.serialization.Serializable

@Serializable
data class Assignment(
    val weightage : String,
    val assignmentScore : String,
    val assignmentTotal : String
)
