package com.yurry.core.data.local

import com.yurry.core.data.local.entity.FavoriteGameEntity
import com.yurry.core.data.local.room.FavoriteGameDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val gameDao: FavoriteGameDao) {
    fun getFavoriteGames(): Flow<List<FavoriteGameEntity>> = gameDao.getAllFavoriteGame()
    fun insertFavoriteGame(gameEntity: FavoriteGameEntity) = gameDao.insertFavoriteGame(gameEntity)
}