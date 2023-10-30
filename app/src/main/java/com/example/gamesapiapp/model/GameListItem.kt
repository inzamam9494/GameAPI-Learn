package com.example.gamesapiapp.model

// step 1 create a model using a json plugin

data class GameListItem(
    val id: Int? = null,
    val title: String? = null,
    val thumbnail: String? = null,
    val developer: String? = null,
    val short_description: String? = null
//    val freetogame_profile_url: String,
//    val game_url: String,
//    val genre: String,
//    val platform: String,
//    val publisher: String,
//    val release_date: String,
)