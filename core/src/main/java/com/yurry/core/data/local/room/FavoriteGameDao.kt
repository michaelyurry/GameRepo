package com.yurry.core.data.local.room

import androidx.room.Dao

@Dao
interface FavoriteGameDao {
    fun getAllFavoriteGame()
}