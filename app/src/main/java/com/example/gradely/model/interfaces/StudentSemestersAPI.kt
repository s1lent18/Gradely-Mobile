package com.example.gradely.model.interfaces

import com.example.gradely.model.models.GetSemesters
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface StudentSemestersAPI {

    @GET("/student/{studentId}/getSemesters")
    suspend fun getSemesters(
        @Header("Authorization") token: String,
        @Path("studentId") studentId: String
    ) : Response<GetSemesters>
}