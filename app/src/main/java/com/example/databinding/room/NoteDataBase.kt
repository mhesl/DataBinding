package com.example.databinding.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import java.security.AccessControlContext

@Database(
    entities = arrayOf(Note::class),
    version = 1
)
abstract class NoteDataBase: RoomDatabase() {

    abstract fun getNodeDao(): NoteDao

    companion object {
        @Volatile private var instance:NoteDataBase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDataBase(context).also{
                instance = it
            }
        }

        private fun buildDataBase(context: Context) = Room.databaseBuilder(
            context.applicationContext ,
            NoteDataBase::class.java ,
            "notedatabase"
        ).build()
    }
}