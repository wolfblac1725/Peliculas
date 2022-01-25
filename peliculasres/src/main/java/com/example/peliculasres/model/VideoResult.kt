package com.example.peliculasres.model

import com.example.datapeliculas.Entity.Video

data class VideoResult(
    val id: Int,
    val results: List<Video>
)