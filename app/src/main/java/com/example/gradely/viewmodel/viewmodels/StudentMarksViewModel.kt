package com.example.gradely.viewmodel.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gradely.model.dataRequests.StudentAllResultRequest
import com.example.gradely.model.interfaces.StudentAllResultAPI
import com.example.gradely.model.interfaces.StudentSemestersAPI
import com.example.gradely.model.models.AllStudentResults
import com.example.gradely.model.models.GetSemesters
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StudentMarksViewModel @Inject constructor(
    private val studentSemestersAPI: StudentSemestersAPI,
    private val studentAllResultAPI: StudentAllResultAPI
) : ViewModel() {

    private val _semestersResult = MutableStateFlow<GetSemesters?>(null)
    val semestersResult: StateFlow<GetSemesters?> = _semestersResult

    private val _allResult = MutableStateFlow<AllStudentResults?>(null)
    val allResult : StateFlow<AllStudentResults?> = _allResult

    fun getSemesters(studentId : String, token: String) {

        viewModelScope.launch {
            try {
                val response = studentSemestersAPI.getSemesters(studentId = studentId, token = token)
                if (response.isSuccessful && response.code() == 200) {
                    response.body()?.let { semesterResponse ->
                        Log.d("Registration Response Body", response.body().toString())
                        _semestersResult.value = semesterResponse
                        Log.d("Registration", "Completed")
                    }
                } else {
                    Log.d("Registration", "Failed")
                }
            } catch (e: Exception) {
                Log.d("Registration", "Failed because of $e")
            }
        }
    }

    fun getAllResults(token: String, request: StudentAllResultRequest) {

        viewModelScope.launch {
            try {
                val response = studentAllResultAPI.getAllResults(token = token, request = request)
                if (response.isSuccessful && response.code() == 200) {
                    response.body()?.let { semesterResponse ->
                        Log.d("Registration Response Body", response.body().toString())
                        _allResult.value = semesterResponse
                        Log.d("Registration", "Completed")
                    }
                } else {
                    Log.d("Registration", "Failed")
                }
            } catch (e: Exception) {
                Log.d("Registration", "Failed because of $e")
            }
        }
    }
}