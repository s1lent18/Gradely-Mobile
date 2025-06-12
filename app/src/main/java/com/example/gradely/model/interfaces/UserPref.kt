package com.example.gradely.model.interfaces

import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow

val STUDENTID_KEY = stringPreferencesKey("studentId")
val STUDENTNAME_KEY = stringPreferencesKey("studentName")
val FATHERNAME_KEY = stringPreferencesKey("fatherName")
val BLOODGROUP_KEY = stringPreferencesKey("bloodGroup")
val ADDRESS_KEY = stringPreferencesKey("address")
val PERSONALEMAIL_KEY = stringPreferencesKey("personalEmail")
val ASSIGNEDEMAIL_KEY = stringPreferencesKey("assignedEmail")
val PHONE_KEY = stringPreferencesKey("phone")
val EMERGENCY_KEY = stringPreferencesKey("emergency")
val BATCH_KEY = stringPreferencesKey("batch")
val DEGREE_KEY = stringPreferencesKey("degree")
val GENDER_KEY = stringPreferencesKey("gender")
val DOB_KEY = stringPreferencesKey("dob")
val TOKEN_KEY = stringPreferencesKey("token")
val TIMESTAMP_KEY = stringPreferencesKey("timestamp")
val STATUS_KEY = stringPreferencesKey("status")

interface UserPref {

    fun getToken(): Flow<String>
    fun getStudentId(): Flow<String>
    fun getStudentName(): Flow<String>
    fun getFatherName(): Flow<String>
    fun getBloodGroup(): Flow<String>
    fun getAddress(): Flow<String>
    fun getPersonalEmail(): Flow<String>
    fun getAssignedEmail(): Flow<String>
    fun getPhone(): Flow<String>
    fun getEmergency(): Flow<String>
    fun getBatch(): Flow<String>
    fun getDegree(): Flow<String>
    fun getGender(): Flow<String>
    fun getDOB(): Flow<String>
    fun getTimeStamp(): Flow<String>
    fun getUserRole(): Flow<String>
    fun getStatus(): Flow<String>

    suspend fun saveStatus(status: String)
    suspend fun saveUserRole(role: String)
    suspend fun saveToken(token: String)
    suspend fun saveStudentId(studentId: String)
    suspend fun saveStudentName(studentName: String)
    suspend fun saveFatherName(fatherName: String)
    suspend fun saveBloodGroup(bloodGroup: String)
    suspend fun saveAddress(address: String)
    suspend fun savePersonalEmail(personalEmail: String)
    suspend fun saveAssignedEmail(assignedEmail: String)
    suspend fun savePhone(phone: String)
    suspend fun saveEmergency(emergency: String)
    suspend fun saveBatch(batch: String)
    suspend fun saveDegree(degree: String)
    suspend fun saveGender(gender: String)
    suspend fun saveDOB(dob: String)
    suspend fun saveTimeStamp(timestamp: String)
}