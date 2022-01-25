package com.example.peliculas.adapters

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.datapeliculas.Entity.MovieEntity
import com.example.peliculas.Constant
import com.example.peliculas.databinding.FragmentItemBinding


class MyMovieRecyclerViewAdapter(
    context: Context
) : RecyclerView.Adapter<MyMovieRecyclerViewAdapter.ViewHolder>() {

    var mOnClickListener:((MovieEntity)->Unit)? = null
    private var movies: List<MovieEntity> = ArrayList()
    private var context:Context
    init {
        this.context=context

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = movies[position]
       Glide.with(context).load(Constant.URL_IMAGE+item.poster_path).into(holder.ivPoster)
        holder.tvDetail.text = item.title
    }

    fun setData(movies: List<MovieEntity>){
        this.movies=movies
        this.notifyDataSetChanged()
    }

    override fun getItemCount(): Int{
        if(movies==null){
            return 0
        }
        return movies.size
    }

    inner class ViewHolder(binding: FragmentItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val ivPoster = binding.ivPoster
        val mView = binding.root
        val tvDetail = binding.tvDetalle

        override fun toString(): String {
            return super.toString()
        }
        init {
            mView.setOnClickListener {
                mOnClickListener?.invoke(movies[adapterPosition])
            }
        }
    }

}