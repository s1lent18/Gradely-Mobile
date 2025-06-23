package com.example.gradely.model.interfaces

import com.example.gradely.model.dataRequests.TeacherLoginRequest
import com.example.gradely.model.models.TeacherLoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface TeacherLoginAPI {

    @POST("/teacher/login")
    suspend fun loginTeacher(
        @Body teacherLoginRequest: TeacherLoginRequest
    ) : Response<TeacherLoginResponse>
}