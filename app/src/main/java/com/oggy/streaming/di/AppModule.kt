package com.oggy.streaming.di

import android.content.Context
import androidx.room.Room
import com.oggy.streaming.database.OggyDatabase
import com.oggy.streaming.network.RetrofitClient
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
    fun provideTmdbApiService() = RetrofitClient.getTmdbApiService()
}
