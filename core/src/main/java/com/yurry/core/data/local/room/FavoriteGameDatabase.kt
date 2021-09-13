package com.yurry.core.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.yurry.core.data.local.entity.FavoriteGameEntity

@Database(entities = [FavoriteGameEntity::class],
    version = 1,
    exportSchema = false)
abstract class FavoriteGameDatabase : RoomDatabase() {
    abstract fun gameDao(): FavoriteGameDao
}