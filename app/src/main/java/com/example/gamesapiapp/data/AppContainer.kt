package com.example.gamesapiapp.data

import com.example.gamesapiapp.network.GameApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// step 4 Converting to json file BoilerPlate Code

interface AppContainer{
    val gameRepository: GameRepository
}

class DefaultAppContainer: AppContainer{
    private val baseUrl = "https://www.freetogame.com/api/"
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(baseUrl)
        .build()

    private val retrofitService: GameApiService by lazy {
        retrofit.create(GameApiService::class.java)
    }
    override val gameRepository: GameRepository by lazy {
        NetworkGameRepository(retrofitService)
    }
}