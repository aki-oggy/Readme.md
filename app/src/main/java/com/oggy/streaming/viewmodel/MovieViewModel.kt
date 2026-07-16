package com.oggy.streaming.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oggy.streaming.model.Movie
import com.oggy.streaming.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {
    
    private val _uiState = MutableStateFlow<MovieUiState>(MovieUiState.Loading)
    val uiState: StateFlow<MovieUiState> = _uiState.asStateFlow()
    
    private val _searchResults = MutableStateFlow<List<Movie>>(emptyList())
    val searchResults: StateFlow<List<Movie>> = _searchResults.asStateFlow()
    
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()
    
    init {
        loadInitialData()
    }
    
    private fun loadInitialData() {
        viewModelScope.launch {
            try {
                _uiState.value = MovieUiState.Loading
                
                // Fetch data from API
                movieRepository.getTrendingMovies()
                movieRepository.getPopularMovies()
                movieRepository.getTopRatedMovies()
                
                // Collect from database
                movieRepository.getAllMovies().collect { movies ->
                    if (movies.isNotEmpty()) {
                        _uiState.value = MovieUiState.Success(movies)
                    } else {
                        _uiState.value = MovieUiState.Empty
                    }
                }
            } catch (e: Exception) {
                _uiState.value = MovieUiState.Error(e.message ?: "Unknown error occurred")
            }
        }
    }
    
    fun searchMovies(query: String) {
        if (query.isBlank()) {
            _searchResults.value = emptyList()
            return
        }
        
        viewModelScope.launch {
            try {
                _isLoading.value = true
                movieRepository.searchMoviesFromApi(query)
                movieRepository.searchMovies(query).collect { results ->
                    _searchResults.value = results
                }
            } catch (e: Exception) {
                _uiState.value = MovieUiState.Error(e.message ?: "Search failed")
            } finally {
                _isLoading.value = false
            }
        }
    }
    
    fun addToFavorites(movie: Movie) {
        viewModelScope.launch {
            try {
                movieRepository.addToFavorites(movie)
            } catch (e: Exception) {
                _uiState.value = MovieUiState.Error("Failed to add to favorites: ${e.message}")
            }
        }
    }
    
    fun removeFromFavorites(movie: Movie) {
        viewModelScope.launch {
            try {
                movieRepository.removeFromFavorites(movie)
            } catch (e: Exception) {
                _uiState.value = MovieUiState.Error("Failed to remove from favorites: ${e.message}")
            }
        }
    }
    
    fun refresh() {
        loadInitialData()
    }
}

sealed class MovieUiState {
    object Loading : MovieUiState()
    object Empty : MovieUiState()
    data class Success(val movies: List<Movie>) : MovieUiState()
    data class Error(val message: String) : MovieUiState()
}
