package com.sundroid.sundroid.viewmodel

import android.app.Application
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.sundroid.sundroid.data.local.dao.SundroidLocalDatabase
import com.sundroid.sundroid.models.RoomUserEntity
import com.sundroid.sundroid.repositories.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch















class SundroidViewModel(application: Application) : AndroidViewModel(application) {
    private val userDao = SundroidLocalDatabase.getDatabase(application).userDao()
    private val userRepository = UserRepository(userDao)
    val isLoading by mutableStateOf(false)
    val isError by mutableStateOf(false)
    var currentUser =  mutableStateOf<RoomUserEntity?>(null);


    var appBarTitle by mutableStateOf("Sundroid")
    val users: Flow<List<RoomUserEntity>> = userRepository.users


    fun insertUser(user: RoomUserEntity) = viewModelScope.launch {
        userRepository.deleteAllUsers()
        userRepository.insertUser(user)
        val streamedUsers = users.collect {
            currentUser.value = it.first()

        }
    }

    fun deleteUser(user: RoomUserEntity) = viewModelScope.launch {
        userRepository.deleteUser(user)
    }
    fun deleteAllUsers() = viewModelScope.launch {
        userRepository.deleteAllUsers()
    }
    fun getCurrentUser() = viewModelScope.launch {


    }
}