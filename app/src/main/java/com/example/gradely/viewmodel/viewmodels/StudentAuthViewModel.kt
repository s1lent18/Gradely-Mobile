package com.example.gradely.viewmodel.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gradely.model.dataRequests.StudentFCMToken
import com.example.gradely.model.dataRequests.StudentLoginRequest
import com.example.gradely.model.dataResponses.NetworkResponse
import com.example.gradely.model.interfaces.StudentLoginApi
import com.example.gradely.model.models.StudentFCMTokenResponse
import com.example.gradely.model.models.StudentLoginResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StudentAuthViewModel @Inject constructor(
    private val studentLoginApi: StudentLoginApi
) : ViewModel() {

    private val _loginResult = MutableStateFlow<NetworkResponse<StudentLoginResponse>?>(null)
    val loginResult: StateFlow<NetworkResponse<StudentLoginResponse>?> = _loginResult

    private val _fcmTokenResult = MutableStateFlow<StudentFCMTokenResponse?>(null)
    val fcmTokenResult : StateFlow<StudentFCMTokenResponse?> = _fcmTokenResult

    fun studentLogin(studentLoginRequest: StudentLoginRequest) {

        _loginResult.value = NetworkResponse.Loading

        Log.d("AuthViewModel", "$studentLoginRequest")

        viewModelScope.launch {
            try {
                val response = studentLoginApi.loginStudent(studentLoginRequest = studentLoginRequest)
                if (response.isSuccessful && response.code() == 200) {
                    response.body()?.let {
                        Log.d("AuthViewModel", "$it")
                        _loginResult.value = NetworkResponse.Success(it)
                    }
                } else {
                    Log.d("AuthViewModel", "Failed")
                    _loginResult.value = NetworkResponse.Failure("Wrong Username / Password")
                }
            } catch (e: Exception) {
                Log.d("AuthViewModel", "$e")
                _loginResult.value = NetworkResponse.Failure("$e")
            }
        }
    }

    fun sendStudentFCMToken(studentFCMToken: StudentFCMToken, studentId : String, token : String) {

        Log.d("FCM", "$studentFCMToken, + $studentId, + $token")

        viewModelScope.launch {
            try {
                val response = studentLoginApi.sendFCMToken(studentId = studentId, token = token, studentFCMToken = studentFCMToken)
                if (response.isSuccessful && response.code() == 200) {
                    response.body()?.let {
                        Log.d("Attendance", response.body().toString())
                        _fcmTokenResult.value = it
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