package com.sundroid.sundroid.practice

import androidx.lifecycle.ViewModel
import com.sundroid.sundroid.models.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow



class PracticeViewModel : ViewModel() {
    private val _user: MutableStateFlow<User?> = MutableStateFlow(null)
    val user: StateFlow<User?> = _user.asStateFlow()
    suspend fun signIn(email: String, displayName: String) {

        _user.value = User(email,  user.value?.displayName+ displayName)
    }
}