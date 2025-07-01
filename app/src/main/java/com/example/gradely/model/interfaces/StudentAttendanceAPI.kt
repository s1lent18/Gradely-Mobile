package com.example.gradely.model.interfaces

import com.example.gradely.model.models.GetAttendances
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface StudentAttendanceAPI {

    @GET("student/{studentId}/getAttendance")
    suspend fun getStudentAttendance(
        @Path("studentId") studentId: String,
        @Header("Authorization") token: String,
    ) : Response<GetAttendances>
}