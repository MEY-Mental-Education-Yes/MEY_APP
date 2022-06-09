package com.candra.projectcapstone.helper

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.candra.projectcapstone.helper.Constant.isCheckedOnBoarding
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

object LocalShared {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "localstore")

    fun getCheckedLocalOnBoarding(context: Context): Flow<Boolean> = context.dataStore.data.map { preferences ->
        preferences[isCheckedOnBoarding]?: false
    }

    suspend fun setCheckedLocalOnBoarding(context: Context) = context.dataStore.edit { preferences ->
        preferences[isCheckedOnBoarding] = true
    }

}