package com.example.flixsterplus

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class TvShowAdapter(
    private val tvShows: List<TvShow>,
    private val onItemClick: (TvShow) -> Unit
) : RecyclerView.Adapter<TvShowAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val poster: ImageView = view.findViewById(R.id.tvShowPoster)
        val title: TextView = view.findViewById(R.id.tvShowTitle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_tv_show, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tvShow = tvShows[position]
        holder.title.text = tvShow.name
        
        tvShow.poster_path?.let {
            Glide.with(holder.itemView.context)
                .load("https://image.tmdb.org/t/p/w500$it")
                .into(holder.poster)
        }
        
        holder.itemView.setOnClickListener { onItemClick(tvShow) }
    }

    override fun getItemCount() = tvShows.size
}
