package com.sundroid.sundroid.screens


import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import com.sundroid.sundroid.models.RoomUserEntity
import com.sundroid.sundroid.viewmodel.SundroidViewModel
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnrememberedMutableState")
@Composable
fun DashBoard(viewModel: SundroidViewModel) {


    LaunchedEffect(true) {
        val user1 = RoomUserEntity(email = "adegbesundayadoyi@gmail.com", name = "Sunday Adegbe");
        viewModel.insertUser(user1)

    }



    if (viewModel.isLoading) {
        // Display a loading indicator while the data is being loaded
        CircularProgressIndicator()
    } else if (viewModel.isError) {
        // Display an error message if there was an error loading the data
        Text(text = "Error loading data")
    } else {
        // Display the list of users if it was loaded successfully

        UserList(viewModel)
    }





























}
@Composable
fun UserList(viewModel: SundroidViewModel) {
    var usersList = viewModel.users.collectAsState(listOf()).value;
    println("felix ${usersList.size}" )
    Column {
        usersList.forEach{ user ->
            UserListItem(user)
        }
    }
}
@Composable
fun UserListItem(user: RoomUserEntity) {
    Row(verticalAlignment = Alignment.CenterVertically) {

        Column {
            Text(text = user.name, fontWeight = FontWeight.Bold)
            Text(text = user.email)
        }
    }

}
fun getList(viewModel: SundroidViewModel) = runBlocking<Unit> {
    val myFlow = flowOf(1, 2, 3)
    myFlow.collect { value ->
        println("Received value: $value")
    }
    println("Flow completed")
}








