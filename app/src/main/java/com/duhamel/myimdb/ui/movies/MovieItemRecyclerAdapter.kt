package com.duhamel.myimdb.ui.movies

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.duhamel.myimdb.R

class MovieItemRecyclerAdapter(private var movies : List<Movie>) : RecyclerView.Adapter<MovieHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        var movieItem = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return MovieHolder(movieItem)
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        var movie = movies[position]
        holder.updateHolder(movie)
    }

    override fun getItemCount(): Int {
        return movies.size
    }
}