package com.duhamel.myimdb.ui.movies

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.commit
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.FragmentTransitionSupport
import com.duhamel.myimdb.R
import com.squareup.picasso.Picasso

class MovieHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val movieImage : ImageView = itemView.findViewById<ImageView>(R.id.movie_image)
    private val movieTitleTextView : TextView = itemView.findViewById<TextView>(R.id.movie_title)
    private val movieYearTextView : TextView = itemView.findViewById<TextView>(R.id.movie_year)
    private val movieRatingTextView : TextView = itemView.findViewById<TextView>(R.id.movie_rating)

    private var currentMovie : Movie? = null;

    fun updateHolder(movie : Movie) {
        currentMovie = movie

        movieTitleTextView.text = movie.title
        movieYearTextView.text = movie.year
        movieRatingTextView.text = movie.imDbRating
        Picasso.get().load(movie.image).into(movieImage)

        val f = MovieDetailsFragment()
        val args = Bundle()
        args.putString("id", movie.id)
        println(movie.id)
        f.arguments = args
        itemView.setOnClickListener{
            (it.context as AppCompatActivity).supportFragmentManager.commit {
                replace(R.id.nav_host_fragment_activity_main, f)
                addToBackStack(null)
            }
        }

    }

}