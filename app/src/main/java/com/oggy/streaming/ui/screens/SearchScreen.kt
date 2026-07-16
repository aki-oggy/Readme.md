package com.oggy.streaming.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.oggy.streaming.model.Movie
import com.oggy.streaming.ui.components.MovieCard
import com.oggy.streaming.viewmodel.MovieViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    viewModel: MovieViewModel,
    onMovieClick: (Movie) -> Unit,
    onTabChange: (Int) -> Unit
) {
    var searchQuery by remember { mutableStateOf("") }
    val searchResults = viewModel.searchResults.collectAsState().value
    val isLoading = viewModel.isLoading.collectAsState().value
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("🔍 Search") }
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = androidx.compose.material3.MaterialTheme.colorScheme.primary,
                contentColor = androidx.compose.material3.MaterialTheme.colorScheme.onPrimary
            ) {
                NavigationBarItem(
                    icon = { Icon(Icons.Filled.Home, contentDescription = "Home") },
                    label = { Text("Home") },
                    selected = false,
                    onClick = { onTabChange(0) }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Filled.Search, contentDescription = "Search") },
                    label = { Text("Search") },
                    selected = true,
                    onClick = { onTabChange(1) }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Filled.Favorite, contentDescription = "Watchlist") },
                    label = { Text("Watchlist") },
                    selected = false,
                    onClick = { onTabChange(2) }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Filled.Settings, contentDescription = "Settings") },
                    label = { Text("Settings") },
                    selected = false,
                    onClick = { onTabChange(3) }
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Search Bar
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { query ->
                    searchQuery = query
                    if (query.isNotEmpty()) {
                        viewModel.searchMovies(query)
                    }
                },
                label = { Text("Search movies...") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
            
            // Search Results
            if (searchResults.isNotEmpty()) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(searchResults) { movie ->
                        MovieCard(
                            movie = movie,
                            onMovieClick = onMovieClick,
                            onFavoriteClick = { clickedMovie ->
                                if (clickedMovie.isFavorite) {
                                    viewModel.removeFromFavorites(clickedMovie)
                                } else {
                                    viewModel.addToFavorites(clickedMovie)
                                }
                            }
                        )
                    }
                }
            } else if (searchQuery.isNotEmpty() && !isLoading) {
                Text("No results found for '$searchQuery'")
            }
        }
    }
}
