package com.oggy.streaming

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.oggy.streaming.ui.screens.HomeScreen
import com.oggy.streaming.ui.screens.SearchScreen
import com.oggy.streaming.ui.screens.SettingsScreen
import com.oggy.streaming.ui.screens.WatchlistScreen
import com.oggy.streaming.ui.theme.OggyTheme
import com.oggy.streaming.viewmodel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OggyTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    var selectedTab by remember { mutableIntStateOf(0) }
                    val movieViewModel: MovieViewModel = hiltViewModel()
                    
                    Scaffold { _ ->
                        when (selectedTab) {
                            0 -> HomeScreen(
                                viewModel = movieViewModel,
                                onMovieClick = { movie ->
                                    // Handle movie click - navigate to details
                                }
                            )
                            1 -> SearchScreen(
                                viewModel = movieViewModel,
                                onMovieClick = { movie ->
                                    // Handle movie click - navigate to details
                                }
                            )
                            2 -> WatchlistScreen(
                                viewModel = movieViewModel,
                                onMovieClick = { movie ->
                                    // Handle movie click - navigate to details
                                }
                            )
                            3 -> SettingsScreen()
                        }
                    }
                }
            }
        }
    }
}
