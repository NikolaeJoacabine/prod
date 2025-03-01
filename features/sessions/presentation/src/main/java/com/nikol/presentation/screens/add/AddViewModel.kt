package com.nikol.presentation.screens.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikol.domain.results.RemoteObtainingGenres
import com.nikol.domain.results.RemoteObtainingMovies
import com.nikol.domain.results.RemoteObtainingSession
import com.nikol.domain.use_cases.AddUserIntoSessionUseCase
import com.nikol.domain.use_cases.GetCommonGenresUseCase
import com.nikol.domain.use_cases.GetLikedMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AddViewModel @Inject constructor(
    private val addMovieUseCase: AddUserIntoSessionUseCase,
    private val getLikedMoviesUseCase: GetLikedMoviesUseCase,
    private val getCommonGenresUseCase: GetCommonGenresUseCase
) : ViewModel() {
    private val _sessionState =
        MutableStateFlow<RemoteObtainingSession>(RemoteObtainingSession.Neutral)
    val sessionState = _sessionState.asStateFlow()

    private val _moviesState =
        MutableStateFlow<RemoteObtainingMovies>(RemoteObtainingMovies.Neutral)
    val moviesState = _moviesState.asStateFlow()

    private val _genresState =
        MutableStateFlow<RemoteObtainingGenres>(RemoteObtainingGenres.Neutral)
    val genresState = _genresState.asStateFlow()

    fun addUser() {
        viewModelScope.launch {
            _sessionState.value = RemoteObtainingSession.Loading
            addMovieUseCase.invoke().let {
                _sessionState.value = it
            }
        }
    }

    fun getMovies() {
        viewModelScope.launch {
            _moviesState.value = RemoteObtainingMovies.Loading
            getLikedMoviesUseCase.invoke().let {
                _moviesState.value = it
            }
        }
    }

    fun getCommonGenres() {
        viewModelScope.launch {
            _genresState.value = RemoteObtainingGenres.Loading
            getCommonGenresUseCase.invoke().let {
                _genresState.value = it
            }
        }
    }

}