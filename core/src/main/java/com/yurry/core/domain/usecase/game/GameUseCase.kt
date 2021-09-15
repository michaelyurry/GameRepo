package com.yurry.core.domain.usecase.game

import com.yurry.core.data.Resource
import com.yurry.core.domain.model.Game
import com.yurry.core.domain.model.GameDetail
import kotlinx.coroutines.flow.Flow

interface GameUseCase {
    fun loadGames(): Flow<Resource<List<Game>>>
    fun loadGameDetail(gameId: Int): Flow<Resource<GameDetail>>

    fun setFavoriteGame(gameDetail: GameDetail, bool: Boolean)
    fun loadFavoriteGames(): Flow<List<Game>>
    fun isFavoriteGame(gameId: Int): Flow<Boolean>

}