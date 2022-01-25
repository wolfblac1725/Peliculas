package com.example.datapeliculas

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.datapeliculas.Entity.MovieEntity
import com.example.datapeliculas.dao.MovieDao

@Database(entities = [MovieEntity::class], version = 1, exportSchema = false)
abstract class MovieRoomDatabase : RoomDatabase() {

    abstract fun getMovieDao(): MovieDao

    companion object{
        @Volatile
        private var INSTANCE:MovieRoomDatabase? = null

        fun getDataBase(context:Context):MovieRoomDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(context.applicationContext,
                    MovieRoomDatabase::class.java,
                    "movies"
                ).allowMainThreadQueries().build()
                INSTANCE = instance
                instance
            }
        }
        fun destroyInstance(){
            INSTANCE = null
        }
    }
}