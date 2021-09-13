package com.yurry.core.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yurry.core.data.local.entity.FavoriteGameEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteGameDao {
    @Query("SELECT * FROM favorite_games")
    fun getAllFavoriteGame(): Flow<List<FavoriteGameEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavoriteGame(game: FavoriteGameEntity)

    @Query("SELECT EXISTS(SELECT * FROM favorite_games WHERE gameId = :id)")
    fun isFavoriteGame(id: Int): Boolean
}