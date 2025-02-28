package com.example.noteswork.repo

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule{

    @Provides
    @Singleton
    suspend fun getDatabase(context: Context): NotesDatabase {
        return Room.databaseBuilder(
            context,
            NotesDatabase::class.java,
            "notes_db"
        ).fallbackToDestructiveMigration().build()
    }
    @Provides
    @Singleton
    suspend fun getDao(database: NotesDatabase): NotesDao {
        return database.notesDao
    }
}