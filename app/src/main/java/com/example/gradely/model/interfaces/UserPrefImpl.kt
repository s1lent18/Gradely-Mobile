package com.example.gradely.model.interfaces

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import com.example.gradely.model.models.StudentData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString
import com.example.gradely.model.objects.UserPreferences.USER_ROLE_KEY

class UserPrefImpl (private val dataStore: DataStore<Preferences>) : UserPref {

    override fun getStudentData(): Flow<StudentData?> {
        return dataStore.data
            .catch {
                emit(emptyPreferences())
            }.map { preferences ->
                preferences[STUDENT_DATA_KEY]?.let {
                    try {
                        Json.decodeFromString<StudentData>(it)
                    } catch (_: Exception) {
                        null
                    }
                }
            }
    }

    override fun getTimeStamp(): Flow<String> {
        return dataStore.data.catch {
            emit(emptyPreferences())
        }.map {
            it[TIMESTAMP_KEY]?: ""
        }
    }

    override fun getUserRole(): Flow<String> {
        return dataStore.data.catch {
            emit(emptyPreferences())
        }.map {
            it[USER_ROLE_KEY]?: ""
        }
    }

    override suspend fun saveStudentData(studentData: StudentData) {
        val jsonString = Json.encodeToString(studentData)
        dataStore.edit { preferences ->
            preferences[STUDENT_DATA_KEY] = jsonString
        }
    }

    override suspend fun saveTimeStamp(timestamp: String) {
        dataStore.edit {
            it[TIMESTAMP_KEY] = timestamp
        }
    }

    override suspend fun saveUserRole(role: String) {
        dataStore.edit {
            it[USER_ROLE_KEY] = role
        }
    }
}