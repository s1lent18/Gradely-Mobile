package com.example.gradely.viewmodel.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    private val _registrationResult = MutableStateFlow<Avaliables?>(null)
    val registrationResult: StateFlow<Avaliables?> = _registrationResult

    fun getRegistration(studentId : String, token: String) {

        Log.d("Registration Check View Model", studentId)

        //_registrationResult.value = NetworkResponse.Loading

        viewModelScope.launch {
            try {
                val response = studentRegistrationAPI.getRegistrations(studentId = studentId, token = token)
                if (response.isSuccessful && response.code() == 200) {
                    response.body()?.let { registrationResponse ->
                        Log.d("Registration Response Body", response.body().toString())
                        _registrationResult.value = registrationResponse
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