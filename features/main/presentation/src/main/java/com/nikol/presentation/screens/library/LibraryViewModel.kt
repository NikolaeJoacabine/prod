package com.nikol.presentation.screens.library

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikol.domain.results.RemoteObtainingLibrary
import com.nikol.domain.results.RemoteObtainingLibraryActionResult
import com.nikol.domain.use_cases.AddInWatchedUseCase
import com.nikol.domain.use_cases.DeleteMovieUseCase
import com.nikol.domain.use_cases.InspectLibraryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream
import javax.inject.Inject

@HiltViewModel
class LibraryViewModel @Inject constructor(
    private val inspectLibraryUseCase: InspectLibraryUseCase,
    private val deleteMovieUseCase: DeleteMovieUseCase,
    private val addInWatchedUseCase: AddInWatchedUseCase
) : ViewModel() {

    private val _libraryState =
        MutableStateFlow<RemoteObtainingLibrary>(RemoteObtainingLibrary.Loading)
    val libraryState = _libraryState.asStateFlow()

    private val _actionState =
        MutableStateFlow<RemoteObtainingLibraryActionResult>(RemoteObtainingLibraryActionResult.Neutral)
    val actionState = _actionState.asStateFlow()


    fun getLibrary() {
        viewModelScope.launch {
            _libraryState.value = RemoteObtainingLibrary.Loading
            inspectLibraryUseCase.invoke().let {
                _libraryState.value = it
            }
        }
    }

    fun deleteMovie(id: Int) {
        viewModelScope.launch {
            _actionState.value = RemoteObtainingLibraryActionResult.Loading
            when (val result = deleteMovieUseCase.invoke(id)) {
                is RemoteObtainingLibraryActionResult.Success -> {
                    getLibrary()
                    _actionState.value = result
                }

                else -> _actionState.value = result
            }
        }
    }

    fun addInWatch(id: Int) {
        viewModelScope.launch {
            _actionState.value = RemoteObtainingLibraryActionResult.Loading
            when (val result = addInWatchedUseCase.invoke(id)) {
                is RemoteObtainingLibraryActionResult.Success -> {
                    getLibrary()
                    _actionState.value = result
                }

                else -> _actionState.value = result
            }
        }
    }
}