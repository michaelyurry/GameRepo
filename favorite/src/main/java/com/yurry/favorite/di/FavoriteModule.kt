package com.yurry.favorite.di

import com.yurry.favorite.ui.FavoriteGameViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoriteModule = module {
    viewModel { FavoriteGameViewModel(get()) }
}