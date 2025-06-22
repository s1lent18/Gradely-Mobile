package com.example.gradely.viewmodel.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gradely.model.dataResponses.NetworkResponse
import com.example.gradely.model.interfaces.StudentRegistrationAPI
import com.example.gradely.model.models.Avaliables
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class StudentRegistrationsViewModel @Inject constructor(
    private val studentRegistrationAPI: StudentRegistrationAPI
) : ViewModel() {

    private val _registrationResult = MutableStateFlow<NetworkResponse<Avaliables>?>(null)
    val registrationResult: StateFlow<NetworkResponse<Avaliables>?> = _registrationResult

    fun getRegistration(studentId : String) {

        _registrationResult.value = NetworkResponse.Loading

        viewModelScope.launch {
            try {
                val response = studentRegistrationAPI.getRegistrations(studentId = studentId)
                if (response.isSuccessful && response.code() == 200) {
                    response.body()?.let {
                        _registrationResult.value = NetworkResponse.Success(it)
                    }
                } else {
                    _registrationResult.value = NetworkResponse.Failure("Wrong Username / Password")
                }
            } catch (e: Exception) {
                _registrationResult.value = NetworkResponse.Failure("$e")
            }
        }
    }
}