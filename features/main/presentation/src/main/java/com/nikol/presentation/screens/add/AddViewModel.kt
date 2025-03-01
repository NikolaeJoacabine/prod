package com.nikol.presentation.screens.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikol.domain.results.RemoteObtainingLibraryActionResult
import com.nikol.domain.use_cases.AddMovieUseCase
import com.nikol.domain.use_cases.DeleteMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddViewModel @Inject constructor(
    private val addMovieUseCase: AddMovieUseCase,
) : ViewModel() {
    private val _actionState =
        MutableStateFlow<RemoteObtainingLibraryActionResult>(RemoteObtainingLibraryActionResult.Neutral)
    val actionState = _actionState.asStateFlow()


    fun addMovie() {
        viewModelScope.launch {
            _actionState.value = RemoteObtainingLibraryActionResult.Loading
            addMovieUseCase.invoke().let {
                _actionState.value = it
            }
        }
    }



}