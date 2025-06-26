package com.example.gradely.model.models

import kotlinx.serialization.Serializable

@Serializable
data class Assignment(
    val assignmentScore : String,
    val assignmentTotal : String
)
