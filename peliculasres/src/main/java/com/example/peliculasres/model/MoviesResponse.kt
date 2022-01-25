package com.example.peliculasres.model

import com.example.datapeliculas.Entity.MovieEntity

data class MoviesResponse(
    val page: Int,
    val results: List<MovieEntity>,
    val total_pages: Int,
    val total_results: Int
)