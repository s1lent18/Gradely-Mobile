package com.example.gradely.model.interfaces

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import com.example.gradely.model.objects.UserPreferences.USER_ROLE_KEY
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

class UserPrefImpl (private val dataStore: DataStore<Preferences>) : UserPref {

    override fun getAddress(): Flow<String> {
        return dataStore.data.catch {
            emit(emptyPreferences())
        }.map {
            it[ADDRESS_KEY]?: ""
        }
    }

    override fun getAssignedEmail(): Flow<String> {
        return dataStore.data.catch {
            emit(emptyPreferences())
        }.map {
            it[ASSIGNEDEMAIL_KEY]?: ""
        }
    }

    override fun getBatch(): Flow<String> {
        return dataStore.data.catch {
            emit(emptyPreferences())
        }.map {
            it[BATCH_KEY]?: ""
        }
    }

    override fun getBloodGroup(): Flow<String> {
        return dataStore.data.catch {
            emit(emptyPreferences())
        }.map {
            it[BLOODGROUP_KEY]?: ""
        }
    }

    override fun getDOB(): Flow<String> {
        return dataStore.data.catch {
            emit(emptyPreferences())
        }.map {
            it[DOB_KEY]?: ""
        }
    }

    override fun getDegree(): Flow<String> {
        return dataStore.data.catch {
            emit(emptyPreferences())
        }.map {
            it[DEGREE_KEY]?: ""
        }
    }

    override fun getEmergency(): Flow<String> {
        return dataStore.data.catch {
            emit(emptyPreferences())
        }.map {
            it[EMERGENCY_KEY]?: ""
        }
    }

    override fun getFatherName(): Flow<String> {
        return dataStore.data.catch {
            emit(emptyPreferences())
        }.map {
            it[FATHERNAME_KEY]?: ""
        }
    }

    override fun getGender(): Flow<String> {
        return dataStore.data.catch {
            emit(emptyPreferences())
        }.map {
            it[GENDER_KEY]?: ""
        }
    }

    override fun getPersonalEmail(): Flow<String> {
        return dataStore.data.catch {
            emit(emptyPreferences())
        }.map {
            it[PERSONALEMAIL_KEY]?: ""
        }
    }

    override fun getPhone(): Flow<String> {
        return dataStore.data.catch {
            emit(emptyPreferences())
        }.map {
            it[PHONE_KEY]?: ""
        }
    }

    override fun getStudentId(): Flow<String> {
        return dataStore.data.catch {
            emit(emptyPreferences())
        }.map {
            it[STUDENTID_KEY]?: ""
        }
    }

    override fun getStudentName(): Flow<String> {
        return dataStore.data.catch {
            emit(emptyPreferences())
        }.map {
            it[STUDENTNAME_KEY]?: ""
        }
    }

    override fun getToken(): Flow<String> {
        return dataStore.data.catch {
            emit(emptyPreferences())
        }.map {
            it[TOKEN_KEY]?: ""
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

    override fun getStatus(): Flow<String> {
        return dataStore.data.catch {
            emit(emptyPreferences())
        }.map {
            it[STATUS_KEY]?: ""
        }
    }

    override suspend fun saveUserRole(role: String) {
        dataStore.edit {
            it[USER_ROLE_KEY] = role
        }
    }

    override suspend fun saveStatus(status: String) {
        dataStore.edit {
            it[STATUS_KEY] = status
        }
    }

    override suspend fun saveTimeStamp(timestamp: String) {
        dataStore.edit {
            it[TIMESTAMP_KEY] = timestamp
        }
    }

    override suspend fun saveAddress(address: String) {
        dataStore.edit {
            it[ADDRESS_KEY] = address
        }
    }

    override suspend fun saveAssignedEmail(assignedEmail: String) {
        dataStore.edit {
            it[ASSIGNEDEMAIL_KEY] = assignedEmail
        }
    }

    override suspend fun saveBatch(batch: String) {
        dataStore.edit {
            it[BATCH_KEY] = batch
        }
    }

    override suspend fun saveBloodGroup(bloodGroup: String) {
        dataStore.edit {
            it[BLOODGROUP_KEY] = bloodGroup
        }
    }

    override suspend fun saveDOB(dob: String) {
        dataStore.edit {
            it[DOB_KEY] = dob
        }
    }

    override suspend fun saveDegree(degree: String) {
        dataStore.edit {
            it[DEGREE_KEY] = degree
        }
    }

    override suspend fun saveEmergency(emergency: String) {
        dataStore.edit {
            it[EMERGENCY_KEY] = emergency
        }
    }

    override suspend fun saveFatherName(fatherName: String) {
        dataStore.edit {
            it[FATHERNAME_KEY] = fatherName
        }
    }

    override suspend fun saveGender(gender: String) {
        dataStore.edit {
            it[GENDER_KEY] = gender
        }
    }

    override suspend fun savePersonalEmail(personalEmail: String) {
        dataStore.edit {
            it[PERSONALEMAIL_KEY] = personalEmail
        }
    }

    override suspend fun savePhone(phone: String) {
        dataStore.edit {
            it[PHONE_KEY] = phone
        }
    }

    override suspend fun saveStudentId(studentId: String) {
        dataStore.edit {
            it[STUDENTID_KEY] = studentId
        }
    }

    override suspend fun saveStudentName(studentName: String) {
        dataStore.edit {
            it[STUDENTNAME_KEY] = studentName
        }
    }

    override suspend fun saveToken(token: String) {
        dataStore.edit {
            it[TOKEN_KEY] = token
        }
    }
}