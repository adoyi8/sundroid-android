package com.sundroid.sundroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.sundroid.sundroid.models.Screen
import com.sundroid.sundroid.practice.PracticeViewModel
import com.sundroid.sundroid.screens.DashBoard
import com.sundroid.sundroid.screens.JobScreen
import com.sundroid.sundroid.screens.StaffScreen
import com.sundroid.sundroid.ui.theme.SundroidTheme
import com.sundroid.sundroid.ui.theme.screens.SplashScreen


class MainActivity : ComponentActivity() {
    private val authViewModel: PracticeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            SundroidTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),

                ) {


                Box(contentAlignment = Alignment.Center){
                    Navigation()
                }






                }
            }
        }
    }

    private fun getGoogleLoginAuth(): GoogleSignInClient {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .requestIdToken(getString(R.string.google_cloud_server_client_id))
            .requestId()
            .requestProfile()
            .build()
        return GoogleSignIn.getClient(this, gso)
    }





    @OptIn(ExperimentalAnimationApi::class)
    @Composable
    fun Navigation() {
        val navController = rememberAnimatedNavController()



        val bottomNavigationItems = listOf(
            Screen.Home,
            Screen.Jobs,
            Screen.Staff
        )
        AnimatedNavHost(navController = navController,
            startDestination = getString(R.string.splash_screen_route)) {
            composable(getString(R.string.splash_screen_route),   enterTransition = {  slideInHorizontally (animationSpec = tween(200)) },
                exitTransition = { slideOutHorizontally(animationSpec = tween(500)) })

             {
                SplashScreen(navController = navController)

            }
            // Main Screen
            composable(getString(R.string.job_screen_route),  enterTransition = {  slideInHorizontally (animationSpec = tween(200)) },
                exitTransition = { slideOutHorizontally(animationSpec = tween(500)) }) {
                JobScreen(navController = navController, items = bottomNavigationItems)
            }
            composable(getString(R.string.staff_screen_route),  enterTransition = {  slideInHorizontally (animationSpec = tween(200)) },
                exitTransition = { slideOutHorizontally(animationSpec = tween(500)) }) {
                StaffScreen(navController = navController, items = bottomNavigationItems)
            }
            composable(getString(R.string.dashboard_route),  enterTransition = {  slideInHorizontally (animationSpec = tween(200)) },
                exitTransition = { slideOutHorizontally(animationSpec = tween(500)) }) {
                DashBoard(navController = navController, items = bottomNavigationItems)
            }
        }
    }
}


