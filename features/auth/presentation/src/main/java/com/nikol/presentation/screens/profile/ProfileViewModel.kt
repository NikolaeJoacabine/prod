package com.nikol.presentation.screens.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikol.data.remote.repository.RemoteProfileFeatureRepository
import com.nikol.domain.respons.RemoteObtainingUserProfile
import com.nikol.domain.use_case.GetProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getProfileUseCase: GetProfileUseCase
) : ViewModel() {
    private val _profileState =
        MutableStateFlow<RemoteObtainingUserProfile>(RemoteObtainingUserProfile.Loading)
    val profileState = _profileState.asStateFlow()

    init {
        getProfile()
    }

    private fun getProfile(){
        viewModelScope.launch {
            _profileState.value = RemoteObtainingUserProfile.Loading
            getProfileUseCase.invoke().let {
                _profileState.value = it
            }
        }
    }
}