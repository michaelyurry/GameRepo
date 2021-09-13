package com.yurry.core.data.remote

import android.util.Log
import com.yurry.core.data.remote.network.ApiResponse
import com.yurry.core.data.remote.network.ApiService
import com.yurry.core.data.remote.response.GameDetailResponse
import com.yurry.core.data.remote.response.GameResponse
import com.yurry.core.util.Constant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {
    suspend fun getAllGames(): Flow<ApiResponse<List<GameResponse>>> {
        //get data from remote api
        return flow {
            try {
                val response = apiService.getGameList(Constant.API_KEY)
                val dataArray = response.results
                if (dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getGameDetail(id: Int): Flow<ApiResponse<GameDetailResponse>> {
        //get data from remote api
        return flow {
            try {
                val response = apiService.getGameDetail(Constant.API_KEY, id)
                if (response.name.isEmpty()){
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}