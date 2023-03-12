package com.sundroid.sundroid.ui.theme.screens

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.sundroid.sundroid.custom_composables.SplashScreenText
import com.sundroid.sundroid.viewmodel.SundroidViewModel
import kotlinx.coroutines.delay


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SplashScreen(navController: NavController, viewModel: SundroidViewModel) {

    var editable by remember { mutableStateOf(true) }
    val delayTime = 350L;
   val startingState = false;
    var sState by remember { mutableStateOf(startingState) }
    var uState by remember { mutableStateOf(startingState) }
    var nState  by remember { mutableStateOf(startingState) }
    var dState  by remember { mutableStateOf(startingState) }
    var rState  by remember { mutableStateOf(startingState) }
    var oState  by remember { mutableStateOf(startingState) }
    var iState  by remember { mutableStateOf(startingState) }
    var d2State  by remember { mutableStateOf(startingState) }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = androidx.compose.ui.Alignment.Center
    ) {
        Row(horizontalArrangement = Arrangement.Center) {
            SplashScreenText(text = "S", visibility =sState )
            SplashScreenText(text = "U", visibility =uState )
            SplashScreenText(text = "N", visibility =nState )
            SplashScreenText(text = "D", visibility =dState )
            SplashScreenText(text = "R", visibility =rState )
            SplashScreenText(text = "O", visibility =oState )
            SplashScreenText(text = "I", visibility =iState )
            SplashScreenText(text = "D", visibility =d2State )

        }



    }





    LaunchedEffect(key1 = true) {
       // while(true) {
            sState = !sState
            delay(delayTime)
            uState = !uState
            delay(delayTime)
            nState = !nState
            delay(delayTime)
            dState = !dState
            delay(delayTime)
            rState = !rState
            delay(delayTime)
            oState = !oState
            delay(delayTime)
            iState = !iState
            delay(delayTime)
            d2State = !d2State
            delay(500)

        var usersList = viewModel.users.collect {
            if(it.isEmpty()) {
                navController.navigate("auth_screen") {
                    popUpTo("splash_screen") { inclusive = true }

                }
            }
            else{
                navController.navigate("dashboard_screen") {
                    popUpTo("splash_screen") { inclusive = true }

                }
            }
        }
    }
}





