package com.example.timerwork

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

class AppDataStore(var context: Context) {
    val Context.ds : DataStore<Preferences> by preferencesDataStore("info")

    companion object{
        val timer_key = intPreferencesKey("timer")
    }

    suspend fun read_timer():Int{
        val data = context.ds.data.first()
        return data[timer_key]?:0
    }

    suspend fun save_timer(timer:Int){
        context.ds.edit {
            it[timer_key] = timer
        }
    }
}