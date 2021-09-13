package com.yurry.gamerepo

import android.app.Application
import com.yurry.core.di.databaseModule
import com.yurry.core.di.networkModule
import com.yurry.core.di.repositoryModule
import com.yurry.gamerepo.di.useCaseModule
import com.yurry.gamerepo.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}