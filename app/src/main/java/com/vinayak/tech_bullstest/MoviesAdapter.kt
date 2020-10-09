package com.vinayak.tech_bullstest

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MoviesAdapter(var context: Context, var moviesList: ArrayList<MovieModel>) : RecyclerView.Adapter<MoviesAdapter.DataHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataHolder
    {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.movies_card, parent, false)

        return DataHolder(view)
    }

    override fun getItemCount(): Int
    {
        return moviesList.size
    }

    override fun onBindViewHolder(holder: DataHolder, position: Int)
    {
        holder.txtMovieName.text = moviesList.get(position).Title
        holder.txtYear.text = moviesList.get(position).Year
        Glide.with(context).load(moviesList.get(position).Poster).into(holder.ivMoviePoster)
    }

    class DataHolder (itemView: View?) : RecyclerView.ViewHolder(itemView!!)
    {
        var ivMoviePoster : ImageView
        var txtMovieName : TextView
        var txtYear : TextView

        init {

            ivMoviePoster = itemView!!.findViewById(R.id.ivMoviePoster)
            txtMovieName = itemView.findViewById(R.id.txtMovieName)
            txtYear = itemView.findViewById(R.id.txtYear)
        }

    }
}