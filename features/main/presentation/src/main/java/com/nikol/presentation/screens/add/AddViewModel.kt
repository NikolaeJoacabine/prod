package com.nikol.presentation.screens.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikol.domain.model.Movie
import com.nikol.domain.results.RemoteObtainingLibrary
import com.nikol.domain.results.RemoteObtainingLibraryActionResult
import com.nikol.domain.use_cases.AddMovieUseCase
import com.nikol.domain.use_cases.AddNewMovieUseCase
import com.nikol.domain.use_cases.SearchFilmsWithApiUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddViewModel @Inject constructor(
    private val addMovieUseCase: AddMovieUseCase,
    private val addNewMovieUseCase: AddNewMovieUseCase,
    private val searchFilmsWithApiUseCase: SearchFilmsWithApiUseCase
) : ViewModel() {
    private val _searchState =
        MutableStateFlow<RemoteObtainingLibrary>(RemoteObtainingLibrary.Success(emptyList()))
    val actionState = _searchState.asStateFlow()

    private val _addingState =
        MutableStateFlow<RemoteObtainingLibraryActionResult>(RemoteObtainingLibraryActionResult.Neutral)
    val addingState = _addingState.asStateFlow()

    fun searchFilms(str: String) {
        viewModelScope.launch {
            _searchState.value = RemoteObtainingLibrary.Loading
            searchFilmsWithApiUseCase.invoke(str).let {
                _searchState.value = it
            }
        }
    }

    fun addMove(id: Int) {
        viewModelScope.launch {
            _addingState.value = RemoteObtainingLibraryActionResult.Loading
            addMovieUseCase.invoke(id).let {
                _addingState.value = it
            }
        }
    }

    fun addNewMovie(
        imageByteArray: ByteArray,
        movie: Movie
    ) {
        _addingState.value = RemoteObtainingLibraryActionResult.Loading
        viewModelScope.launch {
            _addingState.value = RemoteObtainingLibraryActionResult.Loading
            addNewMovieUseCase.invoke(imageByteArray, movie).let {
               _addingState.value = it
            }
        }
    }
}