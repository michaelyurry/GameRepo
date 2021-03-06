package com.yurry.core.di

import androidx.room.Room
import com.yurry.core.data.GameRepositoryImpl
import com.yurry.core.data.local.LocalDataSource
import com.yurry.core.data.local.room.FavoriteGameDatabase
import com.yurry.core.data.remote.RemoteDataSource
import com.yurry.core.data.remote.network.ApiService
import com.yurry.core.domain.repository.GameRepository
import com.yurry.core.util.AppExecutors
import com.yurry.core.util.Constant
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<FavoriteGameDatabase>().gameDao() }
    single {
        val passphrase: ByteArray = SQLiteDatabase.getBytes(Constant.DB_BYTE_KEY.toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(
            androidContext(),
            FavoriteGameDatabase::class.java, "Game.db")
            .fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }
}

val networkModule = module {
    single {
        val hostname = Constant.HOSTNAME
        val certificatePinner = CertificatePinner.Builder()
            .add(hostname, Constant.CERT_1)
            .add(hostname, Constant.CERT_2)
            .build()
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .certificatePinner(certificatePinner)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<GameRepository> {
        GameRepositoryImpl(
            get(),
            get(),
            get()
        )
    }
}