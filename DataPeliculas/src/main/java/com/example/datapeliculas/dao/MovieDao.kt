package com.example.datapeliculas.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.datapeliculas.Entity.MovieEntity

@Dao
interface MovieDao {
    @Query("SELECT * FROM movie")
    fun redNowPlaying(): List<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveMovies(movieEntityList: List<MovieEntity>)
}