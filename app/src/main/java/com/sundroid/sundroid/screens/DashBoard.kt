package com.sundroid.sundroid.screens


import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.sundroid.sundroid.models.RoomUserEntity
import com.sundroid.sundroid.viewmodel.SundroidViewModel
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnrememberedMutableState")
@Composable
fun DashBoard(viewModel: SundroidViewModel, navController: NavController) {


    LaunchedEffect(true) {
      if(viewModel.currentUser.value==null){
          navController.navigate("auth_screen") {
              popUpTo("splash_screen") { inclusive = true }

          }
      }

    }



    if (viewModel.isLoading) {
        // Display a loading indicator while the data is being loaded
        CircularProgressIndicator()
    } else if (viewModel.isError) {
        // Display an error message if there was an error loading the data
        Text(text = "Error loading data")
    } else {
        // Display the list of users if it was loaded successfully
                  Column() {
                      Column() {
                        Text(text="Name")
                          Text(text= viewModel.currentUser.value?.displayName!!)
                      }
                      Column() {
                          Text(text="Email")
                          Text(text= viewModel.currentUser.value?.email!!)
                      }
                      Column() {
                          Text(text="Family Name")
                          Text(text= viewModel.currentUser.value?.familyName!!)
                      }
                      Column() {
                          Text(text="Given Name")
                          Text(text= viewModel.currentUser.value?.givenName!!)
                      }
                      Column() {
                          Text(text="Id Token")
                          Text(text= viewModel.currentUser.value?.idToken!!)
                      }
                      Column() {
                          Text(text="Photo Url")
                          Text(text= viewModel.currentUser.value?.photoUrl!!)
                      }
                      Column() {
                          Text(text="Server Auth Code")
                          Text(text= viewModel.currentUser.value?.serverAuthCode!!)
                      }
                  }

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
            Text(text = user.displayName!!, fontWeight = FontWeight.Bold)
            Text(text = user.email!!)
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








