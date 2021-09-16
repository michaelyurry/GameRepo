package com.yurry.core.data.local.room

import androidx.room.*
import com.yurry.core.data.local.entity.FavoriteGameEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteGameDao {
    @Query("SELECT * FROM favorite_games")
    fun getAllFavoriteGame(): Flow<List<FavoriteGameEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavoriteGame(game: FavoriteGameEntity)

    @Delete
    fun deleteFavoriteGame(game: FavoriteGameEntity)

    @Query("SELECT EXISTS(SELECT * FROM favorite_games WHERE gameId = :id)")
    fun isFavoriteGame(id: Int): Flow<Boolean>
}