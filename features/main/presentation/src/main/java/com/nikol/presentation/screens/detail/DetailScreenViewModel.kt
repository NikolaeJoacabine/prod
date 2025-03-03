package com.nikol.presentation.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nikol.domain.model.Movie
import com.nikol.domain.results.RemoteObtainingLibraryActionResult
import com.nikol.domain.results.RemoteObtainingMovie
import com.nikol.domain.use_cases.AddMovieUseCase
import com.nikol.domain.use_cases.DeleteMovieUseCase
import com.nikol.domain.use_cases.GetFilmUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailScreenViewModel @Inject constructor(
    private val getFilmUseCase: GetFilmUseCase,
    private val addMovieUseCase: AddMovieUseCase,
    private val deleteMovieUseCase: DeleteMovieUseCase
) : ViewModel() {
    private val _filmState =
        MutableStateFlow<RemoteObtainingMovie>(RemoteObtainingMovie.Loading)
    val filmState = _filmState.asStateFlow()

    private val _addState = MutableStateFlow<RemoteObtainingLibraryActionResult>(RemoteObtainingLibraryActionResult.Success)

    fun getFilm(movie: Movie) {
        viewModelScope.launch {
            _filmState.value = RemoteObtainingMovie.Loading
            getFilmUseCase.invoke(movie).let {
                _filmState.value = it
            }
        }
    }

    fun addMovie(id: Int){
        viewModelScope.launch {
            _addState.value = RemoteObtainingLibraryActionResult.Loading
            addMovieUseCase.invoke(id).let {
                _addState.value = it
            }
        }
    }

    fun deleteMove(id: Int){
        viewModelScope.launch {
            _addState.value = RemoteObtainingLibraryActionResult.Loading
            deleteMovieUseCase.invoke(id).let {
                _addState.value = it
            }
        }
    }
}