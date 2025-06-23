package com.example.gradely.viewmodel.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gradely.model.interfaces.UserPref
import com.example.gradely.model.models.StudentData
import com.example.gradely.model.models.TeacherData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class TeacherTokenViewModel @Inject constructor(
    private val userPref: UserPref
) : ViewModel() {

    private val sessionDurationMillis = TimeUnit.MINUTES.toMillis(15)

    private val _session = MutableStateFlow(false)

    val session = _session.onStart {
        if (isSessionExpired()) {
            logout()
        }
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(),
        initialValue = false
    )

    private suspend fun isSessionExpired(): Boolean {
        val loginTimestamp = userPref.getTimeStamp().first().toLongOrNull() ?: return true
        return (System.currentTimeMillis() - loginTimestamp) > sessionDurationMillis
    }

    val teacherData = userPref.getTeacherData().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = null
    )

    val timeStamp = userPref.getTimeStamp().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = ""
    )

    init {
        startAutoLogoutTimer()
    }

    fun saveTimeStamp(timeStamp: String) {
        viewModelScope.launch {
            userPref.saveTimeStamp(timestamp = timeStamp)
        }
    }

    fun saveTeacherData(teacherData: TeacherData) {
        viewModelScope.launch {
            userPref.saveTeacherData(teacherData)
        }
    }

    fun logout() {
        viewModelScope.launch {
            userPref.saveStudentData(StudentData())
            userPref.saveTimeStamp("")
        }
    }

    fun saveUserData(
        teacherData: TeacherData,
        timeStamp: String,
    ) {
        saveTeacherData(teacherData)
        saveTimeStamp(timeStamp)
    }

    private fun startAutoLogoutTimer() {
        viewModelScope.launch {
            delay(900000)
            logout()
        }
    }
}