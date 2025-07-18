package com.example.gradely.viewmodel.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gradely.model.interfaces.StudentAttendanceAPI
import com.example.gradely.model.models.GetAttendances
import com.example.gradely.model.models.StudentFCMTokenResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StudentAttendanceViewModel @Inject constructor(
    private val studentAttendanceAPI: StudentAttendanceAPI
) : ViewModel() {

    private val _attendanceResult = MutableStateFlow<GetAttendances?>(null)
    val attendanceResult: StateFlow<GetAttendances?> = _attendanceResult

    fun getAttendance(studentId : String, token: String) {

        Log.d("Attendance", "${studentId}, $token")

        viewModelScope.launch {
            try {
                val response = studentAttendanceAPI.getStudentAttendance(studentId = studentId, token = token)
                if (response.isSuccessful && response.code() == 200) {
                    response.body()?.let { attendanceResponse ->
                        Log.d("Attendance", response.body().toString())
                        _attendanceResult.value = attendanceResponse
                        Log.d("Attendance", "Completed")
                    }
                } else {
                    Log.d("Attendance", "Failed ${response.body()} ${response.code()}")
                }
            } catch (e: Exception) {
                Log.d("Attendance", "Failed because of $e")
            }
        }
    }
}