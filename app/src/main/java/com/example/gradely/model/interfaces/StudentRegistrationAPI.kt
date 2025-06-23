package com.example.gradely.model.interfaces

import com.example.gradely.model.models.Avaliables
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface StudentRegistrationAPI {

    @GET("/student/{studentId}/allowRegistration")
    suspend fun getRegistrations(
        @Header("Authorization") token: String,
        @Path("studentId") studentId: String
    ) : Response<Avaliables>
}