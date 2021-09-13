package com.yurry.core.data.remote.network

import com.yurry.core.data.remote.response.GameDetailResponse
import com.yurry.core.data.remote.response.GameListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("games")
    suspend fun getGameList(@Query("key") key: String): GameListResponse

    @GET("games/{id}")
    suspend fun getGameDetail(@Query("key") key: String,
                              @Path("id") id: Int): GameDetailResponse
}