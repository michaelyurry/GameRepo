package com.yurry.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class GameResponse(
    @SerializedName("id")
    var gameId: Int,
    var slug: String,
    var name: String,
    var released: String,
    @SerializedName("background_image")
    var background: String,
    var rating: Int,
    @SerializedName("rating_top")
    var ratingTop: Int,
    var metacritic: Int
)

data class GameListResponse(
    var results: List<GameResponse>
)

data class GameDetailResponse(
    @SerializedName("id")
    var gameId: Int,
    var slug: String,
    var name: String,
    var description: String,
    var released: String,
    @SerializedName("background_image")
    var background: String,
    var rating: Int,
    @SerializedName("rating_top")
    var ratingTop: Int,
    var metacritic: Int
)