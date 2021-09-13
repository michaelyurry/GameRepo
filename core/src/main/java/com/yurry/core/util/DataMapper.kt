package com.yurry.core.util

import com.yurry.core.data.local.entity.FavoriteGameEntity
import com.yurry.core.data.remote.response.GameResponse
import com.yurry.core.domain.model.Game

object DataMapper {

    fun mapResponsesToDomain(input: List<GameResponse>): List<Game> {
        val gameList = ArrayList<Game>()
        input.map {
            val game = Game(
                gameId = it.gameId,
                slug = it.slug,
                name = it.name,
                released = it.released,
                background = it.background,
                rating = it.rating,
                ratingTop = it.ratingTop,
                metacritic = it.metacritic
            )
            gameList.add(game)
        }
        return gameList
    }

    fun mapDomainToEntity(input: Game) = FavoriteGameEntity(
        gameId = input.gameId,
        slug = input.slug,
        name = input.name,
        released = input.released,
        background = input.background,
        rating = input.rating,
        ratingTop = input.ratingTop,
        metacritic = input.metacritic
    )
}