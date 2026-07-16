package com.oggy.streaming.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oggy.streaming.model.TvShow
import com.oggy.streaming.repository.TvShowRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TvShowViewModel @Inject constructor(
    private val tvShowRepository: TvShowRepository
) : ViewModel() {
    
    private val _uiState = MutableStateFlow<TvShowUiState>(TvShowUiState.Loading)
    val uiState: StateFlow<TvShowUiState> = _uiState.asStateFlow()
    
    private val _searchResults = MutableStateFlow<List<TvShow>>(emptyList())
    val searchResults: StateFlow<List<TvShow>> = _searchResults.asStateFlow()
    
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()
    
    init {
        loadInitialData()
    }
    
    private fun loadInitialData() {
        viewModelScope.launch {
            try {
                _uiState.value = TvShowUiState.Loading
                tvShowRepository.getTrendingTvShows()
                tvShowRepository.getPopularTvShows()
                tvShowRepository.getTopRatedTvShows()
                
                tvShowRepository.getAllTvShows().collect { shows ->
                    _uiState.value = TvShowUiState.Success(shows)
                }
            } catch (e: Exception) {
                _uiState.value = TvShowUiState.Error(e.message ?: "Unknown error")
            }
        }
    }
    
    fun searchTvShows(query: String) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                tvShowRepository.searchTvShowsFromApi(query)
                tvShowRepository.searchTvShows(query).collect { results ->
                    _searchResults.value = results
                }
            } catch (e: Exception) {
                _uiState.value = TvShowUiState.Error(e.message ?: "Search failed")
            } finally {
                _isLoading.value = false
            }
        }
    }
    
    fun addToFavorites(tvShow: TvShow) {
        viewModelScope.launch {
            tvShowRepository.addToFavorites(tvShow)
        }
    }
    
    fun removeFromFavorites(tvShow: TvShow) {
        viewModelScope.launch {
            tvShowRepository.removeFromFavorites(tvShow)
        }
    }
    
    fun refresh() {
        loadInitialData()
    }
}

sealed class TvShowUiState {
    object Loading : TvShowUiState()
    data class Success(val shows: List<TvShow>) : TvShowUiState()
    data class Error(val message: String) : TvShowUiState()
}
