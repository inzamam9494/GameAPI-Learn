package com.example.gamesapiapp.ui.screens

import android.app.Application
import com.example.gamesapiapp.data.AppContainer
import com.example.gamesapiapp.data.DefaultAppContainer

// step 5

class GameApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}