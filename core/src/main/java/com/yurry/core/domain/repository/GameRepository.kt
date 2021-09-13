package com.yurry.core.domain.repository

import com.yurry.core.data.Resource
import com.yurry.core.domain.model.Game
import com.yurry.core.domain.model.GameDetail
import kotlinx.coroutines.flow.Flow

interface GameRepository {
    fun getGames(): Flow<Resource<List<Game>>>
    fun getGameDetail(): Flow<Resource<GameDetail>>

    fun setFavoriteGame(game: Game)
    fun getFavoriteGames(): Flow<Resource<List<Game>>>
}