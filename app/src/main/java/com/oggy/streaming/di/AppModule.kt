package com.oggy.streaming.di

import android.content.Context
import androidx.room.Room
import com.oggy.streaming.BuildConfig
import com.oggy.streaming.database.OggyDatabase
import com.oggy.streaming.network.RetrofitClient
import com.oggy.streaming.repository.MovieRepository
import com.oggy.streaming.repository.TvShowRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    
    @Singleton
    @Provides
    fun provideOggyDatabase(
        @ApplicationContext context: Context
    ): OggyDatabase {
        return Room.databaseBuilder(
            context,
            OggyDatabase::class.java,
            "oggy_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }
    
    @Singleton
    @Provides
    fun provideMovieDao(database: OggyDatabase) = database.movieDao()
    
    @Singleton
    @Provides
    fun provideTvShowDao(database: OggyDatabase) = database.tvShowDao()
}

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    
    @Singleton
    @Provides
    fun provideTmdbApiKey(): String = BuildConfig.TMDB_API_KEY
    
    @Singleton
    @Provides
    fun provideTmdbApiService() = RetrofitClient.getTmdbApiService()
}

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    
    @Singleton
    @Provides
    fun provideMovieRepository(
        apiService: com.oggy.streaming.network.TmdbApiService,
        movieDao: com.oggy.streaming.database.MovieDao,
        apiKey: String
    ): MovieRepository {
        return MovieRepository(apiService, movieDao, apiKey)
    }
    
    @Singleton
    @Provides
    fun provideTvShowRepository(
        apiService: com.oggy.streaming.network.TmdbApiService,
        tvShowDao: com.oggy.streaming.database.TvShowDao,
        apiKey: String
    ): TvShowRepository {
        return TvShowRepository(apiService, tvShowDao, apiKey)
    }
}
