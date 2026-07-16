package com.oggy.streaming.repository

import com.oggy.streaming.database.TvShowDao
import com.oggy.streaming.model.TvShow
import com.oggy.streaming.network.TmdbApiService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TvShowRepository @Inject constructor(
    private val apiService: TmdbApiService,
    private val tvShowDao: TvShowDao,
    private val apiKey: String
) {
    
    fun getAllTvShows(): Flow<List<TvShow>> = tvShowDao.getAllTvShows()
    
    fun getFavoriteTvShows(): Flow<List<TvShow>> = tvShowDao.getFavoriteTvShows()
    
    fun searchTvShows(query: String): Flow<List<TvShow>> = tvShowDao.searchTvShows(query)
    
    suspend fun getTrendingTvShows(page: Int = 1): Result<Unit> = try {
        val response = apiService.getTrendingTvShows(apiKey, page)
        tvShowDao.insertTvShows(response.results)
        Result.success(Unit)
    } catch (e: Exception) {
        Result.failure(e)
    }
    
    suspend fun getPopularTvShows(page: Int = 1): Result<Unit> = try {
        val response = apiService.getPopularTvShows(apiKey, page)
        tvShowDao.insertTvShows(response.results)
        Result.success(Unit)
    } catch (e: Exception) {
        Result.failure(e)
    }
    
    suspend fun getTopRatedTvShows(page: Int = 1): Result<Unit> = try {
        val response = apiService.getTopRatedTvShows(apiKey, page)
        tvShowDao.insertTvShows(response.results)
        Result.success(Unit)
    } catch (e: Exception) {
        Result.failure(e)
    }
    
    suspend fun searchTvShowsFromApi(query: String, page: Int = 1): Result<Unit> = try {
        val response = apiService.searchTvShows(apiKey, query, page)
        tvShowDao.insertTvShows(response.results)
        Result.success(Unit)
    } catch (e: Exception) {
        Result.failure(e)
    }
    
    suspend fun addToFavorites(tvShow: TvShow) {
        tvShowDao.updateTvShow(tvShow.copy(isFavorite = true))
    }
    
    suspend fun removeFromFavorites(tvShow: TvShow) {
        tvShowDao.updateTvShow(tvShow.copy(isFavorite = false))
    }
}
