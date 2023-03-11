package com.sundroid.sundroid.viewmodel

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.sundroid.sundroid.data.local.dao.SundroidLocalDatabase
import com.sundroid.sundroid.models.User
import com.sundroid.sundroid.repositories.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch















class SundroidViewModel(application: Application) : AndroidViewModel(application) {
    private val userDao = SundroidLocalDatabase.getDatabase(application).userDao()
    private val userRepository = UserRepository(userDao)
    var appBarTitle by mutableStateOf("Sundroid")
    val users: Flow<List<User>> = userRepository.users

    fun insertUser(user: User) = viewModelScope.launch {
        userRepository.insertUser(user)
    }

    fun deleteUser(user: User) = viewModelScope.launch {
        userRepository.deleteUser(user)
    }
}