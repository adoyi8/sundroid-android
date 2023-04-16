package com.sundroid.sundroid.repositories

import com.sundroid.sundroid.data.local.dao.SundroidDao
import com.sundroid.sundroid.data.local.dao.database_models.Job
import com.sundroid.sundroid.data.local.dao.database_models.RoomUserEntity
import com.sundroid.sundroid.data.local.dao.database_models.Shop
import com.sundroid.sundroid.data.local.dao.database_models.Staff
import com.sundroid.sundroid.data.network.SundroidApi
import com.sundroid.sundroid.data.network.model.LoginModel
import com.sundroid.sundroid.data.network.model.LoginResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class SundroidRepository(private val sundroidDao: SundroidDao) {
    val users: Flow<List<RoomUserEntity>> = sundroidDao.getUsers()
    val jobs: Flow<List<Job>> = sundroidDao.getAllJobs()
    val shops: Flow<List<Shop>> = sundroidDao.getAllShops()
    val staff: Flow<List<Staff>> = sundroidDao.getAllStaff()

    suspend fun insertUser(user: RoomUserEntity) {
        sundroidDao.insertUser(user)
    }

    suspend fun deleteUser(user: RoomUserEntity) {
        sundroidDao.deleteUser(user)
    }
    suspend fun deleteAllUsers() {
        sundroidDao.deleteAllUsers()
    }

    suspend fun insertJob(job: Job) {
        sundroidDao.insertJob(job)
    }

    suspend fun updateJob(job: Job) {
        sundroidDao.updateJob(job)
    }
    suspend fun deleteJob(job: Job) {
        sundroidDao.deleteJob(job)
    }
    suspend fun deleteAllJobs() {
        sundroidDao.deleteAllJobs()
    }



    suspend fun insertShop(shop: Shop) {
        sundroidDao.addShop(shop)
    }

    suspend fun updateShop(shop: Shop) {
        sundroidDao.updateShop(shop)
    }
    suspend fun deleteShop(shop: Shop) {
        sundroidDao.removeShop(shop)
    }
    suspend fun deleteAllShops() {
        sundroidDao.deleteAllJobs()
    }


    suspend fun addStaff(staff: Staff) {
        sundroidDao.addStaff(staff)
    }

    suspend fun updateStaff(staff: Staff) {
        sundroidDao.updateStaff(staff)
    }
    suspend fun deleteStaff(staff: Staff) {
        sundroidDao.removeStaff(staff)
    }
    suspend fun deleteAllStaff() {
        sundroidDao.deleteAllStaff()
    }

    suspend fun login(loginModel: LoginModel) : Response<LoginResponse>{
                println("Sam smith start")
                var body = loginModel.getHashMap();
                println("sam smith body $body")
                val response = SundroidApi.retrofitService.authenticate(body)
                // val books = Json.decodeFromString<NetworkResponse>(listResult)
                println("Sam Smith response ${response.body()?.token}")
               if(response.isSuccessful){
                   var user = loginModel.getRoomUserEntity()
                   user.accessToken=response.body()?.token
                   insertUser(user)
               }

        return response

        }

}