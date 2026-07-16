package com.oggy.streaming.repository

import com.oggy.streaming.database.MovieDao
import com.oggy.streaming.model.Movie
import com.oggy.streaming.network.TmdbApiService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val apiService: TmdbApiService,
    private val movieDao: MovieDao,
    private val apiKey: String
) {
    
    fun getAllMovies(): Flow<List<Movie>> = movieDao.getAllMovies()
    
    fun getFavoriteMovies(): Flow<List<Movie>> = movieDao.getFavoriteMovies()
    
    fun searchMovies(query: String): Flow<List<Movie>> = movieDao.searchMovies(query)
    
    suspend fun getTrendingMovies(page: Int = 1): Result<Unit> = try {
        val response = apiService.getTrendingMovies(apiKey, page)
        movieDao.insertMovies(response.results)
        Result.success(Unit)
    } catch (e: Exception) {
        Result.failure(e)
    }
    
    suspend fun getPopularMovies(page: Int = 1): Result<Unit> = try {
        val response = apiService.getPopularMovies(apiKey, page)
        movieDao.insertMovies(response.results)
        Result.success(Unit)
    } catch (e: Exception) {
        Result.failure(e)
    }
    
    suspend fun getTopRatedMovies(page: Int = 1): Result<Unit> = try {
        val response = apiService.getTopRatedMovies(apiKey, page)
        movieDao.insertMovies(response.results)
        Result.success(Unit)
    } catch (e: Exception) {
        Result.failure(e)
    }
    
    suspend fun getUpcomingMovies(page: Int = 1): Result<Unit> = try {
        val response = apiService.getUpcomingMovies(apiKey, page)
        movieDao.insertMovies(response.results)
        Result.success(Unit)
    } catch (e: Exception) {
        Result.failure(e)
    }
    
    suspend fun searchMoviesFromApi(query: String, page: Int = 1): Result<Unit> = try {
        val response = apiService.searchMovies(apiKey, query, page)
        movieDao.insertMovies(response.results)
        Result.success(Unit)
    } catch (e: Exception) {
        Result.failure(e)
    }
    
    suspend fun addToFavorites(movie: Movie) {
        movieDao.updateMovie(movie.copy(isFavorite = true))
    }
    
    suspend fun removeFromFavorites(movie: Movie) {
        movieDao.updateMovie(movie.copy(isFavorite = false))
    }
}
