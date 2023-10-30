package com.example.gamesapiapp.model

// step 1.1 create a model using a json plugin

data class GameList(
    val count: Int,
    val games: List<GameListItem>
)