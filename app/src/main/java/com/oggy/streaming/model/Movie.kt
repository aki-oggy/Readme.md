package com.oggy.streaming.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movies")
data class Movie(
    @PrimaryKey
    @SerializedName("id")
    val id: Int,
    
    @SerializedName("title")
    val title: String,
    
    @SerializedName("overview")
    val overview: String,
    
    @SerializedName("poster_path")
    val posterPath: String?,
    
    @SerializedName("backdrop_path")
    val backdropPath: String?,
    
    @SerializedName("release_date")
    val releaseDate: String,
    
    @SerializedName("vote_average")
    val rating: Double,
    
    @SerializedName("popularity")
    val popularity: Double,
    
    @SerializedName("vote_count")
    val voteCount: Int,
    
    val isFavorite: Boolean = false,
    val addedAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "tv_shows")
data class TvShow(
    @PrimaryKey
    @SerializedName("id")
    val id: Int,
    
    @SerializedName("name")
    val name: String,
    
    @SerializedName("overview")
    val overview: String,
    
    @SerializedName("poster_path")
    val posterPath: String?,
    
    @SerializedName("backdrop_path")
    val backdropPath: String?,
    
    @SerializedName("first_air_date")
    val firstAirDate: String,
    
    @SerializedName("vote_average")
    val rating: Double,
    
    @SerializedName("popularity")
    val popularity: Double,
    
    val isFavorite: Boolean = false,
    val addedAt: Long = System.currentTimeMillis()
)

data class MovieResponse(
    @SerializedName("results")
    val results: List<Movie>,
    
    @SerializedName("page")
    val page: Int,
    
    @SerializedName("total_pages")
    val totalPages: Int,
    
    @SerializedName("total_results")
    val totalResults: Int
)

data class TvShowResponse(
    @SerializedName("results")
    val results: List<TvShow>,
    
    @SerializedName("page")
    val page: Int,
    
    @SerializedName("total_pages")
    val totalPages: Int,
    
    @SerializedName("total_results")
    val totalResults: Int
)
