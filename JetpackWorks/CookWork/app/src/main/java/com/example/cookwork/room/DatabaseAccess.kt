/*package com.example.cookwork.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.cookwork.model.Cooks


@Database(entities = [Cooks::class], version = 1)
abstract class DatabaseAccess : RoomDatabase() {
    abstract fun cooksDao():CookDao

    companion object{
        var INSTANCE:DatabaseAccess? = null

        fun access(context: Context):DatabaseAccess?{
            if (INSTANCE == null){
                synchronized(DatabaseAccess::class){
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        DatabaseAccess::class.java,
                        "yemekler.sqlite"
                    ).createFromAsset("yemekler.sqlite").build()
                }

            }
            return INSTANCE
        }
    }
}

 */