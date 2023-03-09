package com.sundroid.sundroid.screens

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.sundroid.sundroid.models.Screen









@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JobScreen(navController: NavHostController,
              items: List<Screen>){
    Scaffold(
        bottomBar = { AppBottomNavigation(navController = navController, items = items) }
    ) {

        Text(text= "Job Screen")
    }
}