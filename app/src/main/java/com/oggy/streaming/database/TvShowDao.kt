package com.oggy.streaming.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.oggy.streaming.model.TvShow
import kotlinx.coroutines.flow.Flow

@Dao
interface TvShowDao {
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTvShows(tvShows: List<TvShow>)
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTvShow(tvShow: TvShow)
    
    @Query("SELECT * FROM tv_shows ORDER BY popularity DESC")
    fun getAllTvShows(): Flow<List<TvShow>>
    
    @Query("SELECT * FROM tv_shows WHERE isFavorite = 1 ORDER BY addedAt DESC")
    fun getFavoriteTvShows(): Flow<List<TvShow>>
    
    @Query("SELECT * FROM tv_shows WHERE id = :tvShowId")
    suspend fun getTvShowById(tvShowId: Int): TvShow?
    
    @Query("SELECT * FROM tv_shows WHERE name LIKE '%' || :query || '%' ORDER BY popularity DESC")
    fun searchTvShows(query: String): Flow<List<TvShow>>
    
    @Update
    suspend fun updateTvShow(tvShow: TvShow)
    
    @Delete
    suspend fun deleteTvShow(tvShow: TvShow)
    
    @Query("DELETE FROM tv_shows")
    suspend fun clearAllTvShows()
}
