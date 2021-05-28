package com.duhamel.myimdb.ui.movies

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.duhamel.myimdb.R
import com.duhamel.myimdb.databinding.FragmentMoviesBinding
import com.github.kittinunf.fuel.Fuel
import com.google.gson.Gson
import java.io.StringReader

class MoviesFragment : Fragment() {
    private var _binding: FragmentMoviesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val API_KEY = "k_oy5wfs8u"

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_movies, container, false)
        var recyclerView = view.findViewById<RecyclerView>(R.id.movies_recycler_view)
        
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
        val data = sharedPref?.getString("data", null)
        if (data == null) {
            println("No cache")
            Fuel.get("https://imdb-api.com/en/API/MostPopularMovies/$API_KEY")
                .response { _, _, result ->
                    val (bytes, error) =  result
                    if (bytes != null) {
                        var res = Gson().fromJson(bytes.decodeToString(),RequestResult::class.java)
                        if (sharedPref != null) {
                            val gson = Gson()
                            with (sharedPref.edit()) {
                                putString("data", gson.toJson(res))
                                apply()
                            }
                        }
                        recyclerView.apply {
                            layoutManager = LinearLayoutManager(view.context)
                            adapter = MovieItemRecyclerAdapter(res.items)
                        }
                    }
                }
        } else {
            println("Using cached data")
            var res = Gson().fromJson(data, RequestResult::class.java)
            recyclerView.apply {
                layoutManager = LinearLayoutManager(view.context)
                adapter = MovieItemRecyclerAdapter(res.items)
            }
        }
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}