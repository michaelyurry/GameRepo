package com.yurry.gamerepo.di

import com.yurry.core.domain.usecase.game.GameInteractor
import com.yurry.core.domain.usecase.game.GameUseCase
import com.yurry.gamerepo.ui.detail.GameDetailViewModel
import com.yurry.gamerepo.ui.home.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<GameUseCase> { GameInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { GameDetailViewModel(get()) }
}