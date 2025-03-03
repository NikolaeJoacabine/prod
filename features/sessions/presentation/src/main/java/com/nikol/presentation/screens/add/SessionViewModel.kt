package com.nikol.presentation.screens.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikol.domain.results.RemoteObtainingAddResult
import com.nikol.domain.results.RemoteObtainingSession
import com.nikol.domain.use_cases.AddMovieIntoFavoriteUseCase
import com.nikol.domain.use_cases.AddUserIntoSessionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SessionsViewModel @Inject constructor(
    private val addUserIntoSessionUseCase: AddUserIntoSessionUseCase,
    private val addMovieIntoFavoriteUseCase: AddMovieIntoFavoriteUseCase
) : ViewModel() {
    private val _sessionState =
        MutableStateFlow<RemoteObtainingSession>(RemoteObtainingSession.Neutral)
    val sessionState = _sessionState.asStateFlow()

    private val _addState =
        MutableStateFlow<RemoteObtainingAddResult>(RemoteObtainingAddResult.Neutral)

    fun addUser(login: String, genres: List<String>) {
        viewModelScope.launch {
            _sessionState.value = RemoteObtainingSession.Loading
            addUserIntoSessionUseCase.invoke(login, genres).let {
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
    fun addMovie(id: Int) {
        viewModelScope.launch {
            _addState.value = RemoteObtainingAddResult.Loading
            addMovieIntoFavoriteUseCase.invoke(id).let {
                _addState.value = it
            }
        }
    }
}