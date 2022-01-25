package com.example.peliculas.ui.popular

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
import com.example.peliculas.adapters.MyMoviesfavoritesRecyclerViewAdapter
import com.example.peliculas.databinding.FragmentFavoritesListBinding
import com.example.peliculas.ui.moviedetail.DetailMovieActivity
import com.example.peliculas.viewmodel.MoviePopularModel

class MoviesPopularFragment : Fragment() {

    private var columnCount = 2
    private var _binding: FragmentFavoritesListBinding? = null
    private val binding get() = _binding!!
    private var listMovies:List<MovieEntity> = ArrayList<MovieEntity>()
    private lateinit var  moviePopularModel: MoviePopularModel
    private lateinit var adapter: MyMoviesfavoritesRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
        moviePopularModel = ViewModelProvider(this).get(MoviePopularModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding =FragmentFavoritesListBinding.inflate(inflater,container,false)
        val view = binding.root

        adapter= MyMoviesfavoritesRecyclerViewAdapter(requireContext())

        adapter.mOnClickListener={

            val i = Intent(activity, DetailMovieActivity::class.java)

            i.putExtra("movies",it)
            activity?.startActivity(i)

        }
        binding.list.layoutManager=when {
            columnCount <= 1 -> LinearLayoutManager(context)
            else -> GridLayoutManager(context, columnCount)
        }
        binding.list.adapter=adapter
        readMoviesFavorite()


        return view
    }

    private fun readMoviesFavorite() {
        this.moviePopularModel.getPopularMovies().observe(viewLifecycleOwner,{
            listMovies= it
            adapter.setData(it)
        })
    }

    companion object {


        const val ARG_COLUMN_COUNT = "column-count"

        fun newInstance(columnCount: Int) =
            MoviesPopularFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}