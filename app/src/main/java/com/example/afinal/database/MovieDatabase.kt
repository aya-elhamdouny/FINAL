package com.example.afinal.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.afinal.models.Movie
import com.example.afinal.ui.fragments.MovieFragment


@Database(entities = [Movie::class] , version = 1)

abstract class MovieDatabase : RoomDatabase(){

    abstract var MovieDao : MovieDao
    companion object{
        @Volatile
        private lateinit var INSTANCE: MovieDatabase

         fun getDatabase(context: Context): MovieDatabase {
            synchronized(MovieDatabase::class.java) {
                if (!::INSTANCE.isInitialized) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        MovieDatabase::class.java,
                        "Movie.db"
                    ).build()
                }
            }
            return INSTANCE
        }

    }
}