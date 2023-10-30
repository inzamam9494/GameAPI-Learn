package com.example.gamesapiapp.ui.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.gamesapiapp.R
import com.example.gamesapiapp.model.GameListItem

// step 7 create a UI

@Composable
fun HomeScreen(
    gameUiState: GameUiState,
    retryAction: () -> Unit,
    modifier: Modifier
) {
    when(gameUiState){
        is GameUiState.Success -> {
            GameList(games = gameUiState.data, modifier = Modifier)
        }
        is GameUiState.Error -> {
            ErrorScreen(onRetry = retryAction)
        }
        is GameUiState.Loading -> {
            LoadingScreen()
        }
    }
}

//
@Composable
fun GameList(games: List<GameListItem>, modifier: Modifier) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ){
        items(games){game ->
            GameCard(game = game,
                onClick = {},
                modifier = Modifier)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameCard(
    game: GameListItem,
    onClick: (GameListItem) -> Unit,
    modifier: Modifier
) {
    Card(
        modifier = Modifier,
        onClick = { onClick(game) },
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        AsyncImage(model = ImageRequest.Builder(LocalContext.current),
            contentDescription = game.title,
            contentScale = ContentScale.Crop,
            placeholder = painterResource(id = R.drawable.ic_launcher_foreground),
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .border(1.dp, MaterialTheme.colorScheme.onSurface, MaterialTheme.shapes.medium)
        )
    }
}

@Composable
fun ErrorScreen(onRetry: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(imageVector = Icons.Default.Close, contentDescription = null)
        Text(text = stringResource(R.string.loading_failed), modifier = Modifier.padding(16.dp))
        Button(onClick = onRetry) {
            Text(stringResource(R.string.retry))
        }
    }
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Icon(
        modifier = modifier.size(200.dp),
        imageVector = Icons.Default.Info,
        contentDescription = stringResource(R.string.loading)
    )
}

@Preview(showSystemUi = true)
@Composable
fun PreviewScreen() {
    MaterialTheme {
        GameList(
            games = arrayListOf(
            GameListItem(
                title = "Title",
                thumbnail = "https://i.redd.it/1i0r3qj0q8y61.jpg",
                developer = "nokkoi",
                id = 459,
                short_description = "hellaloiya"
            ),
            GameListItem(
                title = "Title",
                thumbnail = "https://i.redd.it/1i0r3qj0q8y61.jpg",
                developer = "nokkoi",
                id = 49,
                short_description = "hellaloiya"
            ),
        ), modifier = Modifier)
    }
}