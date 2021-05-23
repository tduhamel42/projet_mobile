package com.duhamel.myimdb.ui.shows

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.duhamel.myimdb.R
import com.duhamel.myimdb.ui.movies.Movie
import com.duhamel.myimdb.ui.movies.MovieHolder

class ShowItemRecyclerAdapter(private var shows : List<Show>) : RecyclerView.Adapter<ShowHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowHolder {
        var showItem = LayoutInflater.from(parent.context).inflate(R.layout.show_item, parent, false)
        return ShowHolder(showItem)
    }

    override fun onBindViewHolder(holder: ShowHolder, position: Int) {
        var show = shows[position]
        holder.updateHolder(show)
    }

    override fun getItemCount(): Int {
        return shows.size
    }
}