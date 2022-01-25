package com.example.peliculas.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.datapeliculas.Entity.MovieEntity
import com.example.peliculas.repository.MovieRepository


class MoviePopularModel:ViewModel(){

    private val movieRepository: MovieRepository
    private val popularMovies: LiveData<List<MovieEntity>>

    init {
        movieRepository = MovieRepository()
       popularMovies = movieRepository.popularMovies()!!
    }
    fun getPopularMovies(): LiveData<List<MovieEntity>> {
        return popularMovies
    }

}