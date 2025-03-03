package com.nikol.prod

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikol.domain.results.RemoteObtainingCreateUser
import com.nikol.domain.results.RemoteObtainingLoginResult
import com.nikol.domain.use_case.GetCurrentUserUseCase
import com.nikol.domain.use_case.LoginUseCase
import com.nikol.domain.use_case.LogoutUseCase
import com.nikol.domain.use_case.SignupUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthStateViewModel @Inject constructor(
    private val signupUseCase: SignupUseCase,
    private val loginUseCase: LoginUseCase,
    private val logoutUseCase: LogoutUseCase,
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
) : ViewModel() {

    private val _authState = MutableStateFlow<AuthState>(AuthState.Initial)
    val authState: StateFlow<AuthState> = _authState.asStateFlow()

    init {
        checkAuthStatus()
    }

    private fun checkAuthStatus() {
        viewModelScope.launch {
            _authState.value = AuthState.Loading

            // Собираем Flow и получаем текущие credentials
            getCurrentUserUseCase.invoke()
                .collect { credentials ->
                    if (credentials != null) {
                        // Если credentials есть, пытаемся выполнить логин
                        when (val result = loginUseCase.invoke(credentials.first, credentials.second)) {
                            is RemoteObtainingLoginResult.Success -> {
                                _authState.value = AuthState.Authenticated
                            }
                            is RemoteObtainingLoginResult.LoginError -> {
                                logoutUseCase.invoke()
                                _authState.value = AuthState.Unauthenticated
                            }
                            is RemoteObtainingLoginResult.NetworkError -> {
                                _authState.value = AuthState.Error(result.message)
                            }
                            else -> {}
                        }
                    } else {
                        // Если credentials нет, пользователь не аутентифицирован
                        _authState.value = AuthState.Unauthenticated
                    }
                }
        }
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _authState.value = AuthState.Loading
            when (val result = loginUseCase.invoke(email, password)) {
                is RemoteObtainingLoginResult.Success -> {
                    _authState.value = AuthState.Authenticated
                }
                is RemoteObtainingLoginResult.LoginError -> {
                    _authState.value = AuthState.Error(result.message)
                }
                is RemoteObtainingLoginResult.NetworkError -> {
                    _authState.value = AuthState.Error(result.message)
                }
                else -> {}
            }
        }
    }

    fun register(email: String, password: String) {
        viewModelScope.launch {
            _authState.value = AuthState.Loading
            when (val result = signupUseCase.invoke(email, password)) {
                is RemoteObtainingCreateUser.Success -> {
                    _authState.value = AuthState.Authenticated
                }
                is RemoteObtainingCreateUser.SignupError -> {
                    _authState.value = AuthState.Error(result.message)
                }
                is RemoteObtainingCreateUser.NetworkError -> {
                    _authState.value = AuthState.Error(result.message)
                }
                else -> {}
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            logoutUseCase.invoke()
            _authState.value = AuthState.Unauthenticated // Переключаем состояние
        }
    }
}

sealed class AuthState {
    data object Initial : AuthState()
    data object Loading : AuthState()
    data object Authenticated : AuthState()
    data object Unauthenticated : AuthState()
    data class Error(val message: String) : AuthState()
}