package com.sundroid.sundroid.viewmodel

import android.app.Application
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.sundroid.sundroid.data.local.dao.SundroidLocalDatabase
import com.sundroid.sundroid.models.AddJobFormState
import com.sundroid.sundroid.models.BottomSheetAction
import com.sundroid.sundroid.models.Job
import com.sundroid.sundroid.models.RoomUserEntity
import com.sundroid.sundroid.repositories.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch


class SundroidViewModel(application: Application) : AndroidViewModel(application) {



    private val userDao = SundroidLocalDatabase.getDatabase(application).userDao()
    private val userRepository = UserRepository(userDao)
    var isLoading by mutableStateOf(false)
    val isError by mutableStateOf(false)

    var bottomSheetAction = mutableStateOf(BottomSheetAction.ADD_JOB)
    @OptIn(ExperimentalMaterialApi::class)

    val bottomSheetState = mutableStateOf<ModalBottomSheetState>(ModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden))


    var currentUser =  mutableStateOf<RoomUserEntity>(RoomUserEntity(1,"","","","","","",""));
    val formState: AddJobFormState = AddJobFormState();


    var appBarTitle by mutableStateOf("Sundroid")
    val users: Flow<List<RoomUserEntity>> = userRepository.users
    var jobs =  mutableStateListOf<Job>();



    fun insertUser(user: RoomUserEntity) = viewModelScope.launch {
        userRepository.deleteAllUsers()
        userRepository.insertUser(user)
        val streamedUsers = users.collect {
            if(it.isNotEmpty()) {
                currentUser.value = it.first()
            }

        }
    }

    fun deleteUser(user: RoomUserEntity) = viewModelScope.launch {
        userRepository.deleteUser(user)
    }
    fun deleteAllUsers() = viewModelScope.launch {
        isLoading = true;
        userRepository.deleteAllUsers()
        println("delete all users called in sundroid viewmodel")

        isLoading= true
    }
    fun getCurrentUser() = viewModelScope.launch {
        users.collect {
            if(it.isNotEmpty()) {
                currentUser.value = it.first()
            }

        }

    }

    fun logOut()  = viewModelScope.launch{
        println("Blessing 2 log out called in sundroid viewmodel")
        deleteAllUsers()

    }

    fun addJob(){
        var job = Job(customerEmail = formState.email.value, customerName = formState.firstName.value, amount = formState.amount.value.toDouble(), description = formState.jobDescription.value)
        jobs.add(job)
        hideBottomSheet()
        formState.clearForm()
    }

    @OptIn(ExperimentalMaterialApi::class)
    fun showBottomSheet() {
        bottomSheetState.value = ModalBottomSheetState(initialValue =ModalBottomSheetValue.Expanded )
    }

    // Call this method to hide the bottom sheet
    @OptIn(ExperimentalMaterialApi::class)
    fun hideBottomSheet() {
        bottomSheetState.value = ModalBottomSheetState(initialValue =ModalBottomSheetValue.Hidden)
    }
}