package com.sundroid.sundroid.viewmodel

import androidx.lifecycle.ViewModel
import com.sundroid.sundroid.models.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AuthViewModel : ViewModel() {
    private val _user: MutableStateFlow<User?> = MutableStateFlow(null)
    val user: StateFlow<User?> = _user



}