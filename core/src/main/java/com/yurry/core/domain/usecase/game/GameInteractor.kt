package com.yurry.core.domain.usecase.game

import com.yurry.core.data.Resource
import com.yurry.core.domain.model.Game
import com.yurry.core.domain.model.GameDetail
import com.yurry.core.domain.repository.GameRepository
import kotlinx.coroutines.flow.Flow

class GameInteractor(private val repository: GameRepository) : GameUseCase {
    override fun loadGames(): Flow<Resource<List<Game>>> {
        return repository.getGames()
    }

    override fun loadGameDetail(gameId: Int): Flow<Resource<GameDetail>> {
        return repository.getGameDetail(gameId)
    }

    override fun setFavoriteGame(gameDetail: GameDetail, bool: Boolean) {
        return repository.setFavoriteGame(gameDetail, bool)
    }

    override fun loadFavoriteGames(): Flow<List<Game>> {
        return repository.getFavoriteGames()
    }

    override fun isFavoriteGame(gameId: Int): Flow<Boolean> {
        return repository.isFavoriteGame(gameId)
    }
}