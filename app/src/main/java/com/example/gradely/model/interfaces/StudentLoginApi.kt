package com.example.gradely.model.interfaces

import com.example.gradely.model.dataRequests.StudentFCMToken
import com.example.gradely.model.dataRequests.StudentLoginRequest
import com.example.gradely.model.models.StudentFCMTokenResponse
import com.example.gradely.model.models.StudentLoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface StudentLoginApi {

    @POST("/student/login")
    suspend fun loginStudent(
        @Body studentLoginRequest: StudentLoginRequest
    ) : Response<StudentLoginResponse>

    @POST("/student/{studentId}/getFCMToken")
    suspend fun sendFCMToken(
        @Header("Authorization") token: String,
        @Path("studentId") studentId: String,
        @Body studentFCMToken: StudentFCMToken
    ) : Response<StudentFCMTokenResponse>
}