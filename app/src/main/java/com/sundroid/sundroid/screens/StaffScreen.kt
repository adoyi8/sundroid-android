package com.sundroid.sundroid.screens


import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.sundroid.sundroid.custom_composables.StaffList
import com.sundroid.sundroid.custom_composables.SundroidButton
import com.sundroid.sundroid.custom_composables.SundroidText
import com.sundroid.sundroid.models.BottomSheetAction
import com.sundroid.sundroid.viewmodel.SundroidViewModel


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnrememberedMutableState")
@Composable
fun StaffScreen(viewModel: SundroidViewModel, navController: NavController) {



        val staff by viewModel.staff.collectAsState(
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



        if (staff.isNotEmpty()) {

                StaffList(staff, viewModel)

        } else {









                Column( verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.width(400.dp)) {
                        SundroidText("There are no staff currently registered to this shop")
                        var onClick: () -> Unit = {
                                viewModel.bottomSheetAction.value =
                                        BottomSheetAction.ADD_STAFF
                                viewModel.showBottomSheet()
                        }
                        SundroidButton(onClick = onClick, text = "Register Staff")
                }

        }


}









