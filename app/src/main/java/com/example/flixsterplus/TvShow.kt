package com.example.flixsterplus

import java.io.Serializable

data class TvShow(
    val id: Int,
    val name: String,
    val poster_path: String?,
    val backdrop_path: String?,
    val overview: String,
    val vote_average: Double,
    val first_air_date: String
) : Serializable
