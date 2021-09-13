package com.yurry.gamerepo.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.yurry.core.domain.usecase.game.GameUseCase

class HomeViewModel(private val gameUseCase: GameUseCase): ViewModel() {
    val game = gameUseCase.loadGames().asLiveData()
}