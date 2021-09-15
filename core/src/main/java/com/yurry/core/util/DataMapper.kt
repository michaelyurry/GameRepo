package com.yurry.core.util

import com.yurry.core.data.local.entity.FavoriteGameEntity
import com.yurry.core.data.remote.response.GameDetailResponse
import com.yurry.core.data.remote.response.GameResponse
import com.yurry.core.domain.model.Game
import com.yurry.core.domain.model.GameDetail

object DataMapper {

    fun mapGamesResponseToDomain(input: List<GameResponse>): List<Game> {
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

    fun mapGamesEntityToDomain(input: List<FavoriteGameEntity>): List<Game> {
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

    fun mapGameDetailResponseToDomain(input: GameDetailResponse) = GameDetail(
        gameId = input.gameId,
        slug = input.slug,
        name = input.name,
        description = input.description,
        released = input.released,
        background = input.background,
        rating = input.rating,
        ratingTop = input.ratingTop,
        metacritic = input.metacritic
    )

    fun mapDomainToEntity(input: GameDetail) = FavoriteGameEntity(
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