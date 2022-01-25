package com.example.peliculas.ui.moviedetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.datapeliculas.Entity.MovieEntity
import com.example.peliculas.Constant
import com.example.peliculas.R
import com.example.peliculas.databinding.ActivityDetailMovieBinding
import com.example.peliculas.viewmodel.MovieViewModel
import com.google.android.youtube.player.YouTubeStandalonePlayer

class DetailMovieActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailMovieBinding
    private lateinit var  movieViewModel : MovieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        val view =binding.root
        setContentView(view)
        val movie =intent.getSerializableExtra("movies") as MovieEntity

        Glide.with(this).load(Constant.URL_IMAGE+movie.backdrop_path).into(binding.image)
        supportActionBar?.title=movie.title
        binding.tvDetail.text = movie.overview
        movieViewModel = ViewModelProvider(this).get(MovieViewModel::class.java)
        var video = ""
        movieViewModel.getVideoMovie(movie.id).observe(this,{
           if(it.isNotEmpty())
                video = it[it.size-1].key
        })
        binding.tvTrailer.setOnClickListener {
            if(video!=""){
                val i = YouTubeStandalonePlayer
                    .createVideoIntent(this,Constant.API_KEY_YOUTUBE, video,0,true,false)
                startActivity(i)
            }else{
                Toast.makeText(this,getString(R.string.no_tenemos_trailer),Toast.LENGTH_LONG).show()
            }
        }




    }
}