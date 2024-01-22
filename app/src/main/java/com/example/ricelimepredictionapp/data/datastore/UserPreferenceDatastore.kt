package com.example.ricelimepredictionapp.data.datastore

import android.annotation.SuppressLint
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.ricelimepredictionapp.model.LoginResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserPreferenceDatastore(private val context: Context) {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("User")

    suspend fun saveUser(userName: String, userId: String, userToken: String) {
        context.dataStore.edit { preferences ->
            preferences[NAME_KEY] = userName
            preferences[USERID_KEY] = userId
            preferences[TOKEN_KEY] = userToken
        }
    }

    fun getUser(): Flow<LoginResult> {
        return context.dataStore.data.map { preferences ->
            LoginResult(
                preferences[NAME_KEY] ?:"",
                preferences[USERID_KEY] ?:"",
                preferences[TOKEN_KEY] ?:"",
            )
        }
    }

    suspend fun signout() {
        context.dataStore.edit { preferences ->
            preferences[NAME_KEY] = ""
            preferences[USERID_KEY] = ""
            preferences[TOKEN_KEY] = ""
        }
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        @Volatile
        private var INSTANCE: UserPreferenceDatastore? = null

        private val NAME_KEY = stringPreferencesKey("name")
        private val USERID_KEY = stringPreferencesKey("userId")
        private val TOKEN_KEY = stringPreferencesKey("token")

        fun getInstance(contex: Context): UserPreferenceDatastore {
            return INSTANCE ?: synchronized(this) {
                val instance = UserPreferenceDatastore(contex)
                INSTANCE = instance
                instance
            }
        }
    }
}