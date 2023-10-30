package com.example.gamesapiapp.data

import com.example.gamesapiapp.model.GameListItem
import com.example.gamesapiapp.network.GameApiService

// step 3 creating a repository it's help to api data fetch in UI

interface GameRepository {
    suspend fun getGames(count: Int = 25): List<GameListItem>
}

class NetworkGameRepository(
    private val gameApi: GameApiService
) : GameRepository {
    override suspend fun getGames(count: Int): List<GameListItem> {
        return gameApi.getGames(count).games
    }
}
