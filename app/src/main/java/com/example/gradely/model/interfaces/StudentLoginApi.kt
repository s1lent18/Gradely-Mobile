package com.example.gradely.model.interfaces

import com.example.gradely.model.dataRequests.StudentLoginRequest
import com.example.gradely.model.models.StudentLoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface StudentLoginApi {

    @POST("/student/login")
    suspend fun loginStudent(
        @Body studentLoginRequest: StudentLoginRequest
    ) : Response<StudentLoginResponse>
}