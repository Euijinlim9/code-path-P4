package com.example.flixsterplus

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val tvShow = intent.getSerializableExtra("TV_SHOW") as? TvShow

        tvShow?.let {
            findViewById<TextView>(R.id.detailTitle).text = it.name
            findViewById<TextView>(R.id.detailRating).text = "⭐ ${it.vote_average}/10"
            findViewById<TextView>(R.id.detailDate).text = "First Aired: ${it.first_air_date}"
            findViewById<TextView>(R.id.detailOverview).text = it.overview

            it.backdrop_path?.let { path ->
                Glide.with(this)
                    .load("https://image.tmdb.org/t/p/w780$path")
                    .into(findViewById<ImageView>(R.id.detailBackdrop))
            }
        }
    }
}
