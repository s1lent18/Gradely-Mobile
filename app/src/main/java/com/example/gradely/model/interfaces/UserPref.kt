package com.example.gradely.model.interfaces

import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.gradely.model.models.StudentData
import com.example.gradely.model.models.TeacherData
import kotlinx.coroutines.flow.Flow

val STUDENT_DATA_KEY: Preferences.Key<String> = stringPreferencesKey("semesters")
val TEACHER_DATA_KEY: Preferences.Key<String> = stringPreferencesKey("teacherData")
val TIMESTAMP_KEY = stringPreferencesKey("timestamp")

interface UserPref {

    fun getTeacherData(): Flow<TeacherData?>
    fun getStudentData(): Flow<StudentData?>
    fun getUserRole(): Flow<String>
    fun getTimeStamp(): Flow<String>

    suspend fun saveTeacherData(teacherData: TeacherData)
    suspend fun saveStudentData(studentData: StudentData)
    suspend fun saveUserRole(role: String)
    suspend fun saveTimeStamp(timestamp: String)
}