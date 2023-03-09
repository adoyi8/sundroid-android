package com.sundroid.sundroid.ui.theme.screens

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.navigation.NavController
import com.sundroid.sundroid.custom_composables.SplashScreenText
import kotlinx.coroutines.delay


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SplashScreen(navController: NavController) {
    val title = "SUNDROID"
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
            navController.navigate("dashboard_screen"){
                popUpTo("splash_screen") { inclusive = true }
            }

      //  }
        //    navController.navigate("main_screen")
    }



  /*  val title = "SUNDROID"


    var scaleList = ArrayList<Animatable<Float, AnimationVector1D>>()
    var scale = remember {
        androidx.compose.animation.core.Animatable(0f)
    }
    title.forEach {
        scale = remember {
            androidx.compose.animation.core.Animatable(0f)
        }
        scaleList.add(scale)
    }









    Row(
    ){


      title.forEach {
          var scale = remember {
              androidx.compose.animation.core.Animatable(0f)
          }
          Letter(navController = navController, letter = it.toString(), scaleList.get(title.indexOf(it)))

          suspend fun doWorld() {
              delay(2000)
          }
      }



    }

*/
}

@Composable
fun Letter(navController: NavController, letter: String, scale1: Animatable<Float, AnimationVector1D>) {

    // AnimationEffect
    LaunchedEffect(key1 = true) {
        scale1.animateTo(
            targetValue = 0.7f,
            animationSpec = tween(
                durationMillis = 800,
                easing = {
                    OvershootInterpolator(4f).getInterpolation(it)
                })
        )

    //    navController.navigate("main_screen")
    }

    // Image

        Text( text = letter,
            modifier = Modifier.scale(scale1.value))




}