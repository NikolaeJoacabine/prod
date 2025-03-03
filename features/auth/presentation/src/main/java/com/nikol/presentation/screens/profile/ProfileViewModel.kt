package com.nikol.presentation.screens.profile

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nikol.data.remote.repository.RemoteProfileFeatureRepository
import com.nikol.domain.respons.RemoteObtainingUserProfile
import com.nikol.domain.use_case.ExitUseCase
import com.nikol.domain.use_case.GetProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getProfileUseCase: GetProfileUseCase,
    private val exitUseCase: ExitUseCase
) : ViewModel() {
    private val _profileState =
        MutableStateFlow<RemoteObtainingUserProfile>(RemoteObtainingUserProfile.Loading)
    val profileState = _profileState.asStateFlow()

    init {
        getProfile()
    }

    fun exit() {
        viewModelScope.launch {
            exitUseCase.invoke()
        }
    }

    fun getProfile() {
        viewModelScope.launch {
            _profileState.value = RemoteObtainingUserProfile.Loading
            getProfileUseCase.invoke().let {
                _profileState.value = it
                Log.d("profile", it.toString())
            }
        }
    }
}