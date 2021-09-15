package com.yurry.gamerepo.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.yurry.core.data.Resource
import com.yurry.core.domain.model.GameDetail
import com.yurry.core.domain.usecase.game.GameUseCase

class GameDetailViewModel(private val gameUseCase: GameUseCase): ViewModel() {
    fun loadGameDetail(gameId: Int): LiveData<Resource<GameDetail>>{
        return gameUseCase.loadGameDetail(gameId).asLiveData()
    }

    fun isFavoriteGame(gameId: Int): LiveData<Boolean>{
        return gameUseCase.isFavoriteGame(gameId).asLiveData()
    }

    fun setFavoriteGame(gameDetail: GameDetail, bool: Boolean){
        return gameUseCase.setFavoriteGame(gameDetail, bool)
    }
}