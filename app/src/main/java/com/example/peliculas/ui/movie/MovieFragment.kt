package com.example.peliculas.ui.movie

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider

import com.example.datapeliculas.Entity.MovieEntity
import com.example.peliculas.adapters.MyMovieRecyclerViewAdapter
import com.example.peliculas.databinding.FragmentItemListBinding
import com.example.peliculas.ui.moviedetail.DetailMovieActivity
import com.example.peliculas.viewmodel.MovieViewModel


/**
 * A fragment representing a list of Items.
 */
class MovieFragment : Fragment() {

    private var columnCount = 2
    private var _binding: FragmentItemListBinding? = null
    private val binding get() = _binding!!
    private var listMovies:List<MovieEntity> = ArrayList<MovieEntity>()
    private lateinit var  movieViewModel : MovieViewModel
    private lateinit var adapter: MyMovieRecyclerViewAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
        movieViewModel = ViewModelProvider(this).get(MovieViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding =FragmentItemListBinding.inflate(inflater,container,false)
        val view = binding.root

        adapter= MyMovieRecyclerViewAdapter(requireContext())

        adapter.mOnClickListener={

            val i =Intent(activity, DetailMovieActivity::class.java)

            i.putExtra("movies",it)
            activity?.startActivity(i)

        }
        binding.list.layoutManager=when {
            columnCount <= 1 -> LinearLayoutManager(context)
            else -> GridLayoutManager(context, columnCount)
        }
        binding.list.adapter=adapter
        readMoviesNow()
        return view
    }

    private fun readMoviesNow() {
        movieViewModel.getNowPlayingMovies().observe(viewLifecycleOwner,{
            listMovies = it
            adapter.setData(it)
        })
    }

    companion object {

        const val ARG_COLUMN_COUNT = "column-count"


        @JvmStatic
        fun newInstance(columnCount: Int) =
            MovieFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}