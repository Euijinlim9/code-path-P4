package com.example.flixsterplus

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    private val tvShows = mutableListOf<TvShow>()
    private lateinit var adapter: TvShowAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        
        adapter = TvShowAdapter(tvShows) { tvShow ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("TV_SHOW", tvShow)
            startActivity(intent)
        }
        recyclerView.adapter = adapter

        fetchTvShows()
    }

    private fun fetchTvShows() {
        val client = AsyncHttpClient()
        val url = "https://api.themoviedb.org/3/tv/popular?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed"
        
        client.get(url, object : JsonHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Headers, json: JSON) {
                val results = json.jsonObject.getJSONArray("results")
                for (i in 0 until results.length()) {
                    val tvShowJson = results.getJSONObject(i)
                    tvShows.add(parseTvShow(tvShowJson))
                }
                adapter.notifyDataSetChanged()
            }

            override fun onFailure(statusCode: Int, headers: Headers?, response: String?, throwable: Throwable?) {
                throwable?.printStackTrace()
            }
        })
    }

    private fun parseTvShow(json: JSONObject): TvShow {
        return TvShow(
            id = json.getInt("id"),
            name = json.getString("name"),
            poster_path = json.optString("poster_path"),
            backdrop_path = json.optString("backdrop_path"),
            overview = json.getString("overview"),
            vote_average = json.getDouble("vote_average"),
            first_air_date = json.optString("first_air_date", "N/A")
        )
    }
}