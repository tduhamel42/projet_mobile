package com.duhamel.myimdb.ui.movies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.duhamel.myimdb.R
import com.github.kittinunf.fuel.Fuel
import com.google.gson.Gson
import com.squareup.picasso.Picasso

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class MovieDetailsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private val API_KEY = "k_oy5wfs8u"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movie_details, container, false)
        // Inflate the layout for this fragment
        val args = arguments
        val fullTitleTextView = view.findViewById<TextView>(R.id.movie_title_full)
        val plotTextView = view.findViewById<TextView>(R.id.movie_plot)
        val countriesTextView = view.findViewById<TextView>(R.id.movie_countries)
        val starsTextView = view.findViewById<TextView>(R.id.movie_stars)
        val imageView = view.findViewById<ImageView>(R.id.imageView)
        var id : String? = null
        if (args != null) {
            id = args.getString("id")
        }
        arguments?.clear()
        Fuel.get("https://imdb-api.com/en/API/Title/$API_KEY/$id/FullActor,FullCast,Posters,Ratings,")
            .response { _, _, result ->
                val (bytes, error) =  result
                if (bytes != null) {
                    var res = Gson().fromJson(bytes.decodeToString(),MovieDetails::class.java)
                    Picasso.get().load(res.image).into(imageView)
                    fullTitleTextView.text = res.fullTitle
                    plotTextView.text = res.plot
                    starsTextView.text = "Stars: " + res.stars
                    countriesTextView.text = "Countries: " + res.countries
                }
            }
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MovieDetailsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MovieDetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}