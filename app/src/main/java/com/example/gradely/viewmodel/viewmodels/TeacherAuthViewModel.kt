package com.example.gradely.viewmodel.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gradely.model.dataRequests.StudentLoginRequest
import com.example.gradely.model.dataRequests.TeacherLoginRequest
import com.example.gradely.model.dataResponses.NetworkResponse
import com.example.gradely.model.interfaces.TeacherLoginAPI
import com.example.gradely.model.models.TeacherLoginResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TeacherAuthViewModel @Inject constructor(
    private val teacherLoginAPI: TeacherLoginAPI
) : ViewModel() {

    private val _loginResult = MutableStateFlow<NetworkResponse<TeacherLoginResponse>?>(null)
    val loginResult: StateFlow<NetworkResponse<TeacherLoginResponse>?> = _loginResult

    fun teacherLogin(teacherLoginRequest: TeacherLoginRequest) {

        _loginResult.value = NetworkResponse.Loading

        viewModelScope.launch {
            try {
                val response = teacherLoginAPI.loginTeacher(teacherLoginRequest = teacherLoginRequest)
                if (response.isSuccessful && response.code() == 200) {
                    response.body()?.let {
                        _loginResult.value = NetworkResponse.Success(it)
                    }
                } else {
                    _loginResult.value = NetworkResponse.Failure("Wrong Username / Password")
                }
            } catch (e: Exception) {
                _loginResult.value = NetworkResponse.Failure("$e")
            }
        }
    }
    
}