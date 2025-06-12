package com.example.gradely.viewmodel.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gradely.model.interfaces.UserPref
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
class StudentTokenViewModel @Inject constructor(
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

    val token = userPref.getToken().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = ""
    )

    val studentId = userPref.getStudentId().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = ""
    )

    val studentName = userPref.getStudentName().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = ""
    )

    val fatherName = userPref.getFatherName().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = ""
    )

    val bloodGroup = userPref.getBloodGroup().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = ""
    )

    val address = userPref.getAddress().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = ""
    )

    val personalEmail = userPref.getPersonalEmail().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = ""
    )

    val assignedEmail = userPref.getAssignedEmail().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = ""
    )

    val phone = userPref.getPhone().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = ""
    )

    val emergency = userPref.getEmergency().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = ""
    )

    val batch = userPref.getBatch().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = ""
    )

    val degree = userPref.getDegree().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = ""
    )

    val gender = userPref.getGender().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = ""
    )

    val dob = userPref.getDOB().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = ""
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

    fun saveToken(token: String) {
        viewModelScope.launch {
            userPref.saveToken(token = token)
        }
    }

    fun saveStudentId(studentId: String) {
        viewModelScope.launch {
            userPref.saveStudentId(studentId = studentId)
        }
    }

    fun saveStudentName(studentName: String) {
        viewModelScope.launch {
            userPref.saveStudentName(studentName = studentName)
        }
    }

    fun saveFatherName(fatherName: String) {
        viewModelScope.launch {
            userPref.saveFatherName(fatherName = fatherName)
        }
    }

    fun saveBloodGroup(bloodGroup: String) {
        viewModelScope.launch {
            userPref.saveBloodGroup(bloodGroup = bloodGroup)
        }
    }

    fun saveAddress(address: String) {
        viewModelScope.launch {
            userPref.saveAddress(address = address)
        }
    }

    fun savePersonalEmail(personalEmail: String) {
        viewModelScope.launch {
            userPref.savePersonalEmail(personalEmail = personalEmail)
        }
    }

    fun saveAssignedEmail(assignedEmail: String) {
        viewModelScope.launch {
            userPref.saveAssignedEmail(assignedEmail = assignedEmail)
        }
    }

    fun savePhone(phone: String) {
        viewModelScope.launch {
            userPref.savePhone(phone = phone)
        }
    }

    fun saveEmergency(emergency: String) {
        viewModelScope.launch {
            userPref.saveEmergency(emergency = emergency)
        }
    }

    fun saveBatch(batch: String) {
        viewModelScope.launch {
            userPref.saveBatch(batch = batch)
        }
    }

    fun saveDegree(degree: String) {
        viewModelScope.launch {
            userPref.saveDegree(degree = degree)
        }
    }

    fun saveGender(gender: String) {
        viewModelScope.launch {
            userPref.saveGender(gender = gender)
        }
    }

    fun saveDOB(dob: String) {
        viewModelScope.launch {
            userPref.saveDOB(dob = dob)
        }
    }

    fun logout() {
        viewModelScope.launch {
            userPref.saveDOB("")
            userPref.saveToken("")
            userPref.saveStudentId("")
            userPref.saveGender("")
            userPref.saveDegree("")
            userPref.saveBatch("")
            userPref.savePhone("")
            userPref.saveStudentName("")
            userPref.saveFatherName("")
            userPref.saveEmergency("")
            userPref.saveAssignedEmail("")
            userPref.savePersonalEmail("")
            userPref.saveBloodGroup("")
            userPref.saveAddress("")
            userPref.saveTimeStamp("")
        }
    }

    fun saveUserData(
        token: String,
        dob: String,
        studentId: String,
        studentName: String,
        fatherName: String,
        bloodGroup: String,
        gender: String,
        degree: String,
        batch: String,
        phone: String,
        emergency: String,
        personalEmail: String,
        assignedEmail: String,
        address: String,
        timeStamp: String
    ) {
        saveAddress(address)
        saveEmergency(emergency)
        saveToken(token)
        saveBatch(batch)
        saveBloodGroup(bloodGroup)
        savePersonalEmail(personalEmail)
        saveAssignedEmail(assignedEmail)
        savePhone(phone)
        saveDegree(degree)
        saveGender(gender)
        saveStudentName(studentName)
        saveStudentId(studentId)
        saveDOB(dob)
        saveFatherName(fatherName)
        saveTimeStamp(timeStamp)
    }

    private fun startAutoLogoutTimer() {
        viewModelScope.launch {
            delay(900000)
            logout()
        }
    }
}