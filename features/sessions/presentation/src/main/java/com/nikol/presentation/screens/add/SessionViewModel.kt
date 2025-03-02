package com.nikol.presentation.screens.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikol.domain.model.MovieSession
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
class SessionsViewModel @Inject constructor(
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

    fun addUser(login: String, genres: List<String>) {
        viewModelScope.launch {
            _sessionState.value = RemoteObtainingSession.Loading
            addMovieUseCase.invoke(login, genres).let {
                _sessionState.value = it
            }
//            _sessionState.value = RemoteObtainingSession.Success(listOf(
//                MovieSession(
//                    id = 0,
//                    title = "Фильм 1",
//                    year = 1990,
//                    description = "Desc 1",
//                    imageUrl = "https://kinopoiskapiunofficial.tech/images/posters/kp/50510.jpg",
//                    rating = 5.0
//                ),
//                MovieSession(
//                    id = 1,
//                    title = "Фильм 2",
//                    year = 1990,
//                    description = "Desc 1",
//                    imageUrl = "https://kinopoiskapiunofficial.tech/images/posters/kp/50510.jpg",
//                    rating = 5.0
//                ),
//                MovieSession(
//                    id = 2,
//                    title = "Фильм 3",
//                    year = 1990,
//                    description = "Desc 1",
//                    imageUrl = "https://kinopoiskapiunofficial.tech/images/posters/kp/50510.jpg",
//                    rating = 5.0
//                ),
//                MovieSession(
//                    id = 3,
//                    title = "Фильм 4",
//                    year = 1990,
//                    description = "Desc 1",
//                    imageUrl = "https://kinopoiskapiunofficial.tech/images/posters/kp/50510.jpg",
//                    rating = 5.0
//                )
//            ))
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