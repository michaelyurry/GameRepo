package com.yurry.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class GameResponse(
    @SerializedName("id")
    var gameId: Int,
    @SerializedName("slug")
    var slug: String,
    @SerializedName("name")
    var name: String,
    @SerializedName("released")
    var released: String,
    @SerializedName("background_image")
    var background: String,
    @SerializedName("rating")
    var rating: Float,
    @SerializedName("rating_top")
    var ratingTop: Int,
    @SerializedName("metacritic")
    var metacritic: Int
)

data class GameListResponse(
    @SerializedName("results")
    var results: List<GameResponse>
)

data class GameDetailResponse(
    @SerializedName("id")
    var gameId: Int,
    @SerializedName("slug")
    var slug: String,
    @SerializedName("name")
    var name: String,
    @SerializedName("description")
    var description: String,
    @SerializedName("released")
    var released: String,
    @SerializedName("background_image")
    var background: String,
    @SerializedName("rating")
    var rating: Float,
    @SerializedName("rating_top")
    var ratingTop: Int,
    @SerializedName("metacritic")
    var metacritic: Int
)