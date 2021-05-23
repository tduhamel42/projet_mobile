package com.duhamel.myimdb.ui.shows

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.recyclerview.widget.RecyclerView
import com.duhamel.myimdb.R
import com.duhamel.myimdb.ui.movies.Movie
import com.duhamel.myimdb.ui.movies.MovieDetailsFragment
import com.squareup.picasso.Picasso

class ShowHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

    private val showImage : ImageView = itemView.findViewById<ImageView>(R.id.show_image)
    private val showTitleTextView : TextView = itemView.findViewById<TextView>(R.id.show_title)
    private val showYearTextView : TextView = itemView.findViewById<TextView>(R.id.show_year)
    private val showRatingTextView : TextView = itemView.findViewById<TextView>(R.id.show_rating)

    private var currentShow : Show? = null;

    fun updateHolder(show : Show) {
        currentShow = show

        showTitleTextView.text = show.title
        showYearTextView.text = show.year
        showRatingTextView.text = show.imDbRating
        Picasso.get().load(show.image).into(showImage)
        val f = MovieDetailsFragment()
        val args = Bundle()
        args.putString("id", show.id)
        println(show.id)
        f.arguments = args
        itemView.setOnClickListener{
            (it.context as AppCompatActivity).supportFragmentManager.commit {
                replace(R.id.nav_host_fragment_activity_main, f)
                addToBackStack(null)
            }
        }
    }

}