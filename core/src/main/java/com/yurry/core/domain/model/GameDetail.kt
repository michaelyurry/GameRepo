package com.yurry.core.domain.model

data class GameDetail(
    var gameId: Int,
    var slug: String,
    var name: String,
    var description: String,
    var released: String,
    var background: String,
    var rating: Float,
    var ratingTop: Int,
    var metacritic: Int
)