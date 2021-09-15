package com.yurry.core.domain.repository

import com.yurry.core.data.Resource
import com.yurry.core.domain.model.Game
import com.yurry.core.domain.model.GameDetail
import kotlinx.coroutines.flow.Flow

interface GameRepository {
    fun getGames(): Flow<Resource<List<Game>>>
    fun getGameDetail(gameId: Int): Flow<Resource<GameDetail>>

    fun setFavoriteGame(gameDetail: GameDetail, boolean: Boolean)
    fun getFavoriteGames(): Flow<List<Game>>
    fun isFavoriteGame(gameId: Int): Flow<Boolean>
}