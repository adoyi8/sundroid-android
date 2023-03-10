package com.sundroid.sundroid.viewmodel

import androidx.lifecycle.ViewModel
import com.sundroid.sundroid.models.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AuthViewModel : ViewModel() {
    private val _user: MutableStateFlow<User?> = MutableStateFlow(null)
    val user: StateFlow<User?> = _user


    suspend fun signIn(email: String, displayName: String) {
        println("Debby "+ user.value?.displayName)
        _user.value = User(email, user.value?.displayName + displayName)
    }
}