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

    override fun loadGameDetail(): Flow<Resource<GameDetail>> {
        return repository.getGameDetail()
    }

    override fun saveFavoriteGame(game: Game) {
        return repository.setFavoriteGame(game)
    }

    override fun loadFavoriteGames(): Flow<Resource<List<Game>>> {
        return repository.getFavoriteGames()
    }
}