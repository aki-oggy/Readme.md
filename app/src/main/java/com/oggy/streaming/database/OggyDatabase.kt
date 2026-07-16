package com.oggy.streaming.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.oggy.streaming.model.Movie
import com.oggy.streaming.model.TvShow

@Database(
    entities = [Movie::class, TvShow::class],
    version = 1,
    exportSchema = false
)
abstract class OggyDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun tvShowDao(): TvShowDao
}
