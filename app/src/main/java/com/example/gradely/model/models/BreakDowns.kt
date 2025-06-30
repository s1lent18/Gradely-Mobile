package com.example.gradely.model.models

import kotlinx.serialization.Serializable

@Serializable
data class BreakDowns(
    val questionScore : String,
    val questionTotal : String,
    val questionWeightage : String
)