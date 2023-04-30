package com.sundroid.sundroid.viewmodel

import android.app.Application
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.sundroid.sundroid.data.local.dao.SundroidLocalDatabase
import com.sundroid.sundroid.data.local.dao.database_models.Job
import com.sundroid.sundroid.data.local.dao.database_models.RoomUserEntity
import com.sundroid.sundroid.data.local.dao.database_models.Shop
import com.sundroid.sundroid.data.local.dao.database_models.Staff
import com.sundroid.sundroid.data.network.SundroidApi
import com.sundroid.sundroid.data.network.model.LoginModel
import com.sundroid.sundroid.data.network.model.LoginResponse
import com.sundroid.sundroid.models.BottomSheetAction
import com.sundroid.sundroid.models.JobFormState
import com.sundroid.sundroid.models.ShopFormState
import com.sundroid.sundroid.models.StaffFormState
import com.sundroid.sundroid.repositories.SundroidRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody
import retrofit2.Response
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
class SundroidViewModel(application: Application) : AndroidViewModel(application) {
    var navController: NavController? = null;
    private val userDao = SundroidLocalDatabase.getDatabase(application).userDao()
    private val sundroidRepository = SundroidRepository(userDao)
    var isLoading by mutableStateOf(false)
    var animateList by mutableStateOf(false)
    val isError = mutableStateOf(false)
    var bottomSheetAction = mutableStateOf(BottomSheetAction.ADD_JOB)
    @OptIn(ExperimentalMaterialApi::class)
    val bottomSheetState = mutableStateOf<ModalBottomSheetState>(ModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden))
    var currentUser = mutableStateOf<RoomUserEntity>(RoomUserEntity(1, "", "", "", "", "", "", ""));
    var currentJob = mutableStateOf<Job>(Job());
    val jobFormState: JobFormState = JobFormState();
    val shopFormState = ShopFormState();
    val staffFormState = StaffFormState();
    var appBarTitle by mutableStateOf("Sundroid")
    val users: Flow<List<RoomUserEntity>> = sundroidRepository.users
    val jobs: Flow<List<Job>> = sundroidRepository.jobs
    var i = "{\"token\": \"not available\"}";
    var loginResponse = mutableStateOf<Response<LoginResponse>>(
        retrofit2.Response.error(
            1000,
            ResponseBody.create("application/json".toMediaTypeOrNull(), i)
        )
    )
    val shops: Flow<List<Shop>> = sundroidRepository.shops
    val staff: Flow<List<Staff>> = sundroidRepository.staff
    private val _status = MutableLiveData<String>()
    val status: LiveData<String> = _status
    /**
     * Call getMarsPhotos() on init so we can display status immediately.
     */
    fun login(loginModel: LoginModel) {


        viewModelScope.launch {

            loginResponse.value = sundroidRepository.login(loginModel = loginModel)
            println("hakimi 4" + loginResponse)

        }


    }

    fun testConnection() {
        viewModelScope.launch {
            try {
                println("Edsheeran start")
                var body = HashMap<String, String>();
                body.put("name", "Sundroid2020@gmail.com")
                val listResult = SundroidApi.retrofitService.login(body)
                // val books = Json.decodeFromString<NetworkResponse>(listResult)
                println("Edsheeran result ${listResult.token}")
                _status.value = "Edsheeran: ${listResult} Mars photos retrieved"
            } catch (e: Exception) {
                _status.value = "Edsheeran Failure: ${e.message}"
            }
        }
    }

    fun insertUser(user: RoomUserEntity) = viewModelScope.launch {
        sundroidRepository.deleteAllUsers()
        sundroidRepository.insertUser(user)
        val streamedUsers = users.collect {
            if (it.isNotEmpty()) {
                currentUser.value = it.first()
            }
        }
    }

    fun deleteUser(user: RoomUserEntity) = viewModelScope.launch {
        sundroidRepository.deleteUser(user)
    }

    fun deleteAllUsers() = viewModelScope.launch {
        isLoading = true;
        sundroidRepository.deleteAllUsers()
        isLoading = true
    }
    fun deleteAllJobs() = viewModelScope.launch {
        isLoading = true;
        sundroidRepository.deleteAllJobs()
        isLoading = true
        isLoading = true
    }

    fun getCurrentUser() = viewModelScope.launch {
        users.collect {
            if (it.isNotEmpty()) {
                currentUser.value = it.first()
            }

        }
    }

    fun logOut() = viewModelScope.launch {
        println("Blessing 2 log out called in sundroid view-model")
        deleteAllUsers()

    }


    fun addJob() = viewModelScope.launch {
        jobFormState.timeReceived.value =
            LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
        sundroidRepository.insertJob(jobFormState.getJobFromFormState())
        jobs.collect {
            if (it.isNotEmpty() && !it.last().syncStatus) {
                println("Droid Name"+ it.last().customerName)
                println("Droid Id"+ it.last().jobId)
                addJobApi(it.last())
            }
        }
        hideBottomSheet()
        jobFormState.clearForm()
    }

    fun addShop() = viewModelScope.launch {
        currentUser.value.email.let {
            shopFormState.owner.value = currentUser.value.email
        }
        sundroidRepository.insertShop(shopFormState.getShopFromFormState())
        hideBottomSheet()
        shopFormState.clearForm()
    }

    @OptIn(ExperimentalMaterialApi::class)
    fun showBottomSheet() {
        bottomSheetState.value =
            ModalBottomSheetState(initialValue = ModalBottomSheetValue.Expanded)
    }

    // Call this method to hide the bottom sheet
    @OptIn(ExperimentalMaterialApi::class)
    fun hideBottomSheet() {
        bottomSheetState.value = ModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    }

    fun updateJob() = viewModelScope.launch {
        sundroidRepository.updateJob(jobFormState.getJobFromFormState())
        hideBottomSheet()
        jobFormState.clearForm()
    }

    fun addJobApi(job: Job) {


        viewModelScope.launch {

            var response = sundroidRepository.addJobApi(job = job)
            if(response.isSuccessful){
                var returnedJob = response.body();
                returnedJob?.syncStatus = true;
                sundroidRepository.updateJob(returnedJob!!)
            }

        }


    }
}