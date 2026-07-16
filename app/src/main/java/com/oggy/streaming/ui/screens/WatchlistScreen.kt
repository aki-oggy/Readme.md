package com.oggy.streaming.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.oggy.streaming.model.Movie
import com.oggy.streaming.ui.components.MovieCard
import com.oggy.streaming.viewmodel.MovieViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WatchlistScreen(
    viewModel: MovieViewModel,
    onMovieClick: (Movie) -> Unit
) {
    val favorites = viewModel.uiState.collectAsState().value
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("My Watchlist") }
            )
        }
    ) { paddingValues ->
        // For this example, we'll show a placeholder
        // In a real app, you'd fetch favorites from the repository
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            Text("Watchlist feature coming soon!")
        }
    }
}
