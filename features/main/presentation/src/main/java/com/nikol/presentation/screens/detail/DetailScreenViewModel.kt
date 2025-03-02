package com.nikol.presentation.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikol.domain.model.Movie
import com.nikol.domain.results.RemoteObtainingMovie
import com.nikol.domain.use_cases.GetFilmUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailScreenViewModel @Inject constructor(
    private val getFilmUseCase: GetFilmUseCase
) : ViewModel() {

    private val _filmState =
        MutableStateFlow<RemoteObtainingMovie>(RemoteObtainingMovie.Loading)
    val filmState = _filmState.asStateFlow()

    fun getFilm(movie: Movie) {
        viewModelScope.launch {
            _filmState.value = RemoteObtainingMovie.Loading
            getFilmUseCase.invoke(movie).let {
                _filmState.value = it
            }
        }
    }
}