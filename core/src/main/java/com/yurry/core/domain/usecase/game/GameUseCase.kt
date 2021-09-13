package com.yurry.core.domain.usecase.game

import androidx.lifecycle.LiveData
import com.yurry.core.data.Resource
import com.yurry.core.domain.model.Game
import com.yurry.core.domain.model.GameDetail
import kotlinx.coroutines.flow.Flow

interface GameUseCase {
    fun loadGames(): Flow<Resource<List<Game>>>
    fun loadGameDetail(): Flow<Resource<GameDetail>>

    fun saveFavoriteGame(game: Game)
    fun loadFavoriteGames(): Flow<Resource<List<Game>>>
}