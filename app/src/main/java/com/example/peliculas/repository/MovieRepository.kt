package com.example.peliculas.repository

import android.widget.Toast

import androidx.lifecycle.MutableLiveData
import com.example.datapeliculas.Entity.MovieEntity
import com.example.datapeliculas.Entity.Video
import com.example.datapeliculas.MovieRoomDatabase
import com.example.datapeliculas.dao.MovieDao
import com.example.peliculas.app.MyApp
import com.example.peliculasres.MovieApiService
import com.example.peliculasres.MovieClient
import com.example.peliculasres.model.MoviesResponse
import com.example.peliculasres.model.VideoResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MovieRepository() {
    private var movieApiService: MovieApiService? = null
    private var movieClient: MovieClient? = null
    private var nowPlaying: MutableLiveData<List<MovieEntity>>? = null
    private var popularMovie: MutableLiveData<List<MovieEntity>>? = null
    private val movieDao: MovieDao

    init {
        movieClient = MovieClient.instance
        movieApiService = movieClient?.getMovieServices()
        nowPlaying = nowPlayingMovies()
        popularMovie = popularMovies()
        movieDao = MovieRoomDatabase.getDataBase(MyApp.instance).getMovieDao()
    }

    fun nowPlayingMovies(): MutableLiveData<List<MovieEntity>>? {
        if (nowPlaying == null)
            nowPlaying = MutableLiveData<List<MovieEntity>>()

        val call: Call<MoviesResponse>? = movieApiService?.readNowPlaying()
        call?.enqueue(object : Callback<MoviesResponse> {
            override fun onResponse(
                call: Call<MoviesResponse>,
                response: Response<MoviesResponse>
            ) {
                if (response.isSuccessful) {
                    nowPlaying?.value = response.body()?.results
                    Thread {
                        movieDao.saveMovies(response.body()?.results!!)
                    }.start()
                }
            }

            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                Toast.makeText(MyApp.instance, "Error de correxion", Toast.LENGTH_LONG).show()
                nowPlaying?.value = movieDao.redNowPlaying()
            }

        })
        return nowPlaying
    }

    fun readVideos(movieId :Int): MutableLiveData<List<Video>>?{
        var video = MutableLiveData<List<Video>>()
        val call : Call<VideoResult>? = movieApiService?.readVideo(movieId)
        call?.enqueue(object : Callback<VideoResult>{
            override fun onResponse(call: Call<VideoResult>, response: Response<VideoResult>) {
               if(response.isSuccessful){
                   video.value = response.body()?.results
               }
            }

            override fun onFailure(call: Call<VideoResult>, t: Throwable) {
                Toast.makeText(MyApp.instance, "Error de correxion", Toast.LENGTH_LONG).show()
            }

        })
        return video
    }

    fun popularMovies(): MutableLiveData<List<MovieEntity>>? {
        if (popularMovie == null)
            popularMovie = MutableLiveData<List<MovieEntity>>()

        val call: Call<MoviesResponse>? = movieApiService?.readPopularMovie()
        call?.enqueue(object : Callback<MoviesResponse> {
            override fun onResponse(
                call: Call<MoviesResponse>,
                response: Response<MoviesResponse>
            ) {
                if (response.isSuccessful) {
                    popularMovie?.value = response.body()?.results

                }
            }

            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                Toast.makeText(MyApp.instance, "Error de correxion", Toast.LENGTH_LONG).show()
            }

        })
        return popularMovie
    }
}