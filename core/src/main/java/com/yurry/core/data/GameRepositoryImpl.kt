package com.yurry.core.data

import com.yurry.core.data.local.LocalDataSource
import com.yurry.core.data.remote.RemoteDataSource
import com.yurry.core.data.remote.network.ApiResponse
import com.yurry.core.domain.model.Game
import com.yurry.core.domain.model.GameDetail
import com.yurry.core.domain.repository.GameRepository
import com.yurry.core.util.AppExecutors
import com.yurry.core.util.DataMapper
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
                val gameList = DataMapper.mapGamesResponseToDomain(result.data)
                emit(Resource.Success(gameList))
            } else {
                emit(Resource.Error<List<Game>>("Error"))
            }
        }
    }

    override fun getGameDetail(gameId: Int): Flow<Resource<GameDetail>> {
        return flow {
            emit(Resource.Loading())
            val result = remoteDataSource.getGameDetail(gameId).first()
            if (result is ApiResponse.Success){
                val gameDetail = DataMapper.mapGameDetailResponseToDomain(result.data)
                emit(Resource.Success(gameDetail))
            } else {
                emit(Resource.Error<GameDetail>("Error"))
            }
        }
    }

    override fun setFavoriteGame(gameDetail: GameDetail, bool: Boolean) {
        val gameEntity = DataMapper.mapDomainToEntity(gameDetail)
        if (bool){
            appExecutors.diskIO().execute { localDataSource.insertFavoriteGame(gameEntity) }
        } else {
            appExecutors.diskIO().execute { localDataSource.deleteFavoriteGame(gameEntity) }
        }
    }

    override fun getFavoriteGames(): Flow<List<Game>> {
        return localDataSource.getFavoriteGames().map {
            DataMapper.mapGamesEntityToDomain(it)
        }
    }

    override fun isFavoriteGame(gameId: Int): Flow<Boolean> {
        return localDataSource.isFavoriteGame(gameId)
    }
}