package com.duhamel.myimdb.ui.shows

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.duhamel.myimdb.R
import com.duhamel.myimdb.databinding.FragmentShowsBinding
import com.duhamel.myimdb.ui.movies.MovieItemRecyclerAdapter
import com.github.kittinunf.fuel.Fuel
import com.google.gson.Gson

class ShowsFragment : Fragment() {

    private lateinit var showsViewModel: ShowsViewModel
    private var _binding: FragmentShowsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val API_KEY = "k_oy5wfs8u"

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        showsViewModel =
                ViewModelProvider(this).get(ShowsViewModel::class.java)

        _binding = FragmentShowsBinding.inflate(inflater, container, false)
        var view = inflater.inflate(R.layout.fragment_movies, container, false)
        var recyclerView = view.findViewById<RecyclerView>(R.id.movies_recycler_view)

        Fuel.get("https://imdb-api.com/en/API/Top250TVs/$API_KEY")
            .response { _, _, result ->
                val (bytes, error) =  result
                if (bytes != null) {
                    var res = Gson().fromJson(bytes.decodeToString(), RequestResult::class.java)
                    recyclerView.apply {
                        layoutManager = LinearLayoutManager(view.context)
                        adapter = ShowItemRecyclerAdapter(res.items)
                    }
                }
            }
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}