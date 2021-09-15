package com.yurry.favorite.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.yurry.core.domain.usecase.game.GameUseCase

class FavoriteGameViewModel(gameUseCase: GameUseCase): ViewModel() {
    val favoriteGames = gameUseCase.loadFavoriteGames().asLiveData()
}