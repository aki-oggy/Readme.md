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
                movieRepository.getTrendingMovies()
                movieRepository.getPopularMovies()
                movieRepository.getTopRatedMovies()
                movieRepository.getUpcomingMovies()
                
                movieRepository.getAllMovies().collect { movies ->
                    _uiState.value = MovieUiState.Success(movies)
                }
            } catch (e: Exception) {
                _uiState.value = MovieUiState.Error(e.message ?: "Unknown error")
            }
        }
    }
    
    fun searchMovies(query: String) {
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
            movieRepository.addToFavorites(movie)
        }
    }
    
    fun removeFromFavorites(movie: Movie) {
        viewModelScope.launch {
            movieRepository.removeFromFavorites(movie)
        }
    }
    
    fun refresh() {
        loadInitialData()
    }
}

sealed class MovieUiState {
    object Loading : MovieUiState()
    data class Success(val movies: List<Movie>) : MovieUiState()
    data class Error(val message: String) : MovieUiState()
}
