package com.sundroid.sundroid.screens


import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.sundroid.sundroid.custom_composables.JobList
import com.sundroid.sundroid.custom_composables.SundroidButton
import com.sundroid.sundroid.custom_composables.SundroidText
import com.sundroid.sundroid.models.BottomSheetAction
import com.sundroid.sundroid.viewmodel.SundroidViewModel


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnrememberedMutableState")
@Composable
fun JobScreen(viewModel: SundroidViewModel, navController: NavController) {


        val jobs by viewModel.jobs.collectAsState(
                initial = emptyList()
        )


        LaunchedEffect(true) {
                viewModel.getCurrentUser();
                if (viewModel.currentUser.value == null) {
                        navController.navigate("auth_screen") {
                                popUpTo("splash_screen") { inclusive = true }
                        }
                }
        }

        if (jobs.isNotEmpty()) {

                JobList(jobs, viewModel)

        } else {
                Box(contentAlignment = Alignment.Center){
                        Column(verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.width(400.dp)) {
                                SundroidText("There are currently no jobs available")
                                var onClick: () -> Unit = {
                                        viewModel.bottomSheetAction.value =
                                                BottomSheetAction.ADD_JOB
                                                viewModel.showBottomSheet()
                                }
                                SundroidButton(onClick = onClick, text = "New Job")
                        }
                }
        }


}









