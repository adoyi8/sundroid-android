package com.sundroid.sundroid.screens


import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import com.sundroid.sundroid.custom_composables.ShopList
import com.sundroid.sundroid.custom_composables.SundroidButton
import com.sundroid.sundroid.custom_composables.SundroidText
import com.sundroid.sundroid.data.local.dao.database_models.RoomUserEntity
import com.sundroid.sundroid.models.BottomSheetAction
import com.sundroid.sundroid.viewmodel.SundroidViewModel
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnrememberedMutableState")
@Composable
fun DashBoard(viewModel: SundroidViewModel, navController: NavController) {


    val jobs by viewModel.jobs.collectAsState(
        initial = emptyList()
    )
    val shops by viewModel.shops.collectAsState(
        initial = emptyList()
    )

    LaunchedEffect(true) {

        viewModel.getCurrentUser();
        println("Enzo " + viewModel.currentUser.value.email)
        if (viewModel.currentUser.value == null) {
            navController.navigate("auth_screen") {
                popUpTo("splash_screen") { inclusive = true }

            }
        }

    }

    var myshops = shops.filter { i -> i.owner == viewModel.currentUser.value.email }

    if (myshops.isNotEmpty()) {

        ShopList(shops, viewModel)

    } else {
        Box(contentAlignment = Alignment.Center){
        Column {
            SundroidText("You currently are not registered to any shop")
            var onClick: () -> Unit = {
                viewModel.bottomSheetAction.value =
                    BottomSheetAction.ADD_SHOP
                viewModel.showBottomSheet()

            }
            SundroidButton(onClick = onClick, text = "Register New Shop")
        }
        }
    }


}


@Composable
fun UserList(viewModel: SundroidViewModel) {
    var usersList = viewModel.users.collectAsState(listOf()).value;
    println("felix ${usersList.size}")
    Column {
        usersList.forEach { user ->
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








