package com.example.gradely.model.interfaces

import com.example.gradely.model.dataRequests.StudentAllResultRequest
import com.example.gradely.model.models.AllStudentResults
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface StudentAllResultAPI {

    @POST("/student/getAllResults")
    suspend fun getAllResults(
        @Header("Authorization") token: String,
        @Body request: StudentAllResultRequest
    ) : Response<AllStudentResults>
}