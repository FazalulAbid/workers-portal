package com.fifty.workersportal.util

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class TokenManager(private val context: Context) {
    companion object {
        private val TOKEN_KEY = stringPreferencesKey("jwt_token")
    }

//    fun getToken(): Flow<String?> {
//        return context.dataStore.data.map { preference ->
//            preference[TOKEN_KEY]
//        }
//    }
//
//    suspend fun saveToken(token: String) {
//        context.dataStore.edit { preference ->
//            preference[TOKEN_KEY]
//        }
//    }
//
//    suspend fun deleteToken() {
//        context.dataStore.edit { preference ->
//            preference.remove(TOKEN_KEY)
//        }
//    }
}