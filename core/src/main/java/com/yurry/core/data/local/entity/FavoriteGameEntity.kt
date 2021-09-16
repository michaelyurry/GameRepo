package com.yurry.core.data.local.entity

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_games")
data class FavoriteGameEntity(
    @PrimaryKey
    @NonNull
    var gameId: Int,
    var slug: String,
    var name: String,
    var released: String,
    var background: String,
    var rating: Float,
    var ratingTop: Int,
    var metacritic: Int
)