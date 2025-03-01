package com.nikol.presentation.screens.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ProfileViewModel @Inject constructor(
) : ViewModel() {
//    private val _userNameState =
//        MutableStateFlow<String>(userName)
//    val userNameState = _userNameState.asStateFlow()
//
//    fun setUserName(newUserName: String) {
//        viewModelScope.launch {
//            _userNameState.value = newUserName
//        }
//    }
}