package com.example.peliculasres

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MovieClient {
    private val movieApiService: MovieApiService
    private val retrofit: Retrofit


    companion object{
        var instance: MovieClient? = null
            get() {
                if(field == null){
                    instance = MovieClient()
                }
                return field
            }

    }

    init {
        val okHttpClientBuider = OkHttpClient.Builder()
        okHttpClientBuider.addInterceptor(RequestInterceptor())

        val client = okHttpClientBuider.build()
        retrofit = Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        movieApiService = retrofit.create(MovieApiService::class.java)
    }
    fun getMovieServices()=movieApiService
}