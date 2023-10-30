package com.example.gamesapiapp.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.gamesapiapp.data.GameRepository
import com.example.gamesapiapp.model.GameListItem
import kotlinx.coroutines.launch
import java.io.IOException

// step 6 Creating a ViewModel

sealed interface GameUiState {
    data class Success(val data: List<GameListItem>) : GameUiState
    object Error : GameUiState
    object Loading : GameUiState
}

class GameViewModel(
    private val gameRepository: GameRepository
) : ViewModel() {
    var gameUiState: GameUiState by mutableStateOf(GameUiState.Loading)
        private set

    init {
        getGames()
    }

    fun getGames() {
        viewModelScope.launch {
            try {
                gameUiState = GameUiState.Success(gameRepository.getGames())
            } catch (e: IOException) {
                gameUiState = GameUiState.Error
            } catch (e: Exception) {
                gameUiState = GameUiState.Error
            }
        }
    }

    companion object{
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as GameApplication)
                val gameRepository = application.container.gameRepository
                GameViewModel(gameRepository = gameRepository)
            }
        }
    }

}
