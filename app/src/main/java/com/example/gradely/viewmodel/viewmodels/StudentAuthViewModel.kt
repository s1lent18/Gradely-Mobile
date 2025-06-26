package com.example.gradely.viewmodel.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gradely.model.dataRequests.StudentLoginRequest
import com.example.gradely.model.dataResponses.NetworkResponse
import com.example.gradely.model.interfaces.StudentLoginApi
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
}