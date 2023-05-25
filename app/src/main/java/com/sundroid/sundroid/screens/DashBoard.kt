package com.sundroid.sundroid.screens


import android.annotation.SuppressLint
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.sundroid.sundroid.R
import com.sundroid.sundroid.viewmodel.SundroidViewModel











@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnrememberedMutableState")
@Composable
fun DashBoard(viewModel: SundroidViewModel, navController: NavController) {


    if(false){
    navController.navigate(stringResource(id = R.string.job_screen_route)) {
        popUpTo("splash_screen") { inclusive = true }

    }}
        else{

        }
}







