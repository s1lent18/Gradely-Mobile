package com.example.gradely.viewmodel.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gradely.model.interfaces.StudentSemestersAPI
import com.example.gradely.model.models.GetSemesters
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StudentMarksViewModel @Inject constructor(
    private val studentSemestersAPI: StudentSemestersAPI
) : ViewModel() {

    private val _semestersResult = MutableStateFlow<GetSemesters?>(null)
    val semestersResult: StateFlow<GetSemesters?> = _semestersResult

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
}