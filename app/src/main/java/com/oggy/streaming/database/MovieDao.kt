package com.oggy.streaming.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.oggy.streaming.model.Movie
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<Movie>)
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: Movie)
    
    @Query("SELECT * FROM movies ORDER BY popularity DESC")
    fun getAllMovies(): Flow<List<Movie>>
    
    @Query("SELECT * FROM movies WHERE isFavorite = 1 ORDER BY addedAt DESC")
    fun getFavoriteMovies(): Flow<List<Movie>>
    
    @Query("SELECT * FROM movies WHERE id = :movieId")
    suspend fun getMovieById(movieId: Int): Movie?
    
    @Query("SELECT * FROM movies WHERE title LIKE '%' || :query || '%' ORDER BY popularity DESC")
    fun searchMovies(query: String): Flow<List<Movie>>
    
    @Update
    suspend fun updateMovie(movie: Movie)
    
    @Delete
    suspend fun deleteMovie(movie: Movie)
    
    @Query("DELETE FROM movies")
    suspend fun clearAllMovies()
}
