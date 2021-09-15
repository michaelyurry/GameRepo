package com.yurry.core.domain.model

data class Game(
    var gameId: Int,
    var slug: String,
    var name: String,
    var released: String,
    var background: String,
    var rating: Float,
    var ratingTop: Int,
    var metacritic: Int
)