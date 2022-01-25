package com.example.peliculas.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import com.example.datapeliculas.Entity.MovieEntity
import com.example.datapeliculas.Entity.Video
import com.example.peliculas.repository.MovieRepository


class MovieViewModel : ViewModel() {
    private val movieRepository: MovieRepository
    private val nowPlayingMovies: LiveData<List<MovieEntity>>

    init {
        movieRepository = MovieRepository()
       nowPlayingMovies = movieRepository.nowPlayingMovies()!!
    }
    fun getNowPlayingMovies():LiveData<List<MovieEntity>>{
        return nowPlayingMovies
    }

    fun getVideoMovie(movieId :Int):LiveData<List<Video>>{
        return movieRepository.readVideos(movieId)!!
    }
}