package com.example.peliculasres

import com.example.peliculasres.model.MoviesResponse
import com.example.peliculasres.model.VideoResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieApiService {

    @GET("movie/now_playing")
    fun readNowPlaying(): Call<MoviesResponse>

    @GET("movie/popular")
    fun readPopularMovie(): Call<MoviesResponse>

    @GET("movie/popular")
    fun readPopular(): Call<MoviesResponse>

    @GET("movie/{movieId}/videos")
    fun readVideo(@Path("movieId") movieId :Int): Call<VideoResult>

}