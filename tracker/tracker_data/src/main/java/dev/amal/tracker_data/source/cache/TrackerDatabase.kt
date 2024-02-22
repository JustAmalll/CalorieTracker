package dev.amal.tracker_data.source.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.amal.tracker_data.source.cache.entity.TrackedFoodEntity

@Database(entities = [TrackedFoodEntity::class], version = 1)
abstract class TrackerDatabase : RoomDatabase() {
    abstract val dao: TrackerDao
}