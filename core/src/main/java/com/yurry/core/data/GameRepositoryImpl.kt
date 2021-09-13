package com.yurry.core.data

import com.yurry.core.data.local.LocalDataSource
import com.yurry.core.data.remote.RemoteDataSource
import com.yurry.core.data.remote.network.ApiResponse
import com.yurry.core.domain.model.Game
import com.yurry.core.domain.model.GameDetail
import com.yurry.core.domain.repository.GameRepository
import com.yurry.core.util.AppExecutors
import com.yurry.core.util.DataMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

class GameRepositoryImpl(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val appExecutors: AppExecutors

): GameRepository {
    override fun getGames(): Flow<Resource<List<Game>>> {
        return flow {
            emit(Resource.Loading())
            val result = remoteDataSource.getAllGames().first()
            if (result is ApiResponse.Success){
                val gameList = DataMapper.mapResponsesToDomain(result.data)
                emit(Resource.Success(gameList))
            }
        }.flowOn(Dispatchers.IO)
    }

    override fun getGameDetail(): Flow<Resource<GameDetail>> {
        TODO("Not yet implemented")
    }

    override fun setFavoriteGame(game: Game) {
        val gameEntity = DataMapper.mapDomainToEntity(game)
        appExecutors.diskIO().execute { localDataSource.insertFavoriteGame(gameEntity) }
    }

    override fun getFavoriteGames(): Flow<Resource<List<Game>>> {
        TODO("Not yet implemented")
    }
}