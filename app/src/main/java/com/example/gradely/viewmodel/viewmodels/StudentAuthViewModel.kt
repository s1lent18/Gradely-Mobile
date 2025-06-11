package com.example.gradely.viewmodel.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gradely.model.dataRequests.StudentLoginRequest
import com.example.gradely.model.dataResponses.NetworkResponse
import com.example.gradely.model.interfaces.StudentLoginApi
import com.example.gradely.model.models.StudentLoginResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StudentAuthViewModel @Inject constructor(
    private val studentLoginApi: StudentLoginApi
) : ViewModel() {

    private val _loginResult = MutableLiveData<NetworkResponse<StudentLoginResponse>>()
    val loginResult: LiveData<NetworkResponse<StudentLoginResponse>> = _loginResult

    fun studentLogin(studentLoginRequest: StudentLoginRequest) {

        _loginResult.value = NetworkResponse.Loading

        viewModelScope.launch {
            try {
                val response = studentLoginApi.loginStudent(studentLoginRequest = studentLoginRequest)

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