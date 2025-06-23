package com.example.gradely.model.models

import kotlinx.serialization.Serializable

@Serializable
data class Department(
    val deptId: String = "",
    val deptName: String = ""
)