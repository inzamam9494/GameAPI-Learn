package com.example.gamesapiapp.network

import com.example.gamesapiapp.model.GameList
import retrofit2.http.GET
import retrofit2.http.Path

// step 2 create interface for defined Api end points which helps creating a functions

interface GameApiService {
    @GET("games/{count}")
    suspend fun getGames(
        @Path("count") count: Int
    ): GameList
}