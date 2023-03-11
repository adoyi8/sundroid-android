package com.sundroid.sundroid

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.sundroid.sundroid.models.Screen
import com.sundroid.sundroid.practice.PracticeViewModel
import com.sundroid.sundroid.screens.AppBottomNavigation
import com.sundroid.sundroid.screens.DashBoard
import com.sundroid.sundroid.screens.JobScreen
import com.sundroid.sundroid.screens.StaffScreen
import com.sundroid.sundroid.ui.theme.SundroidTheme
import com.sundroid.sundroid.ui.theme.screens.SplashScreen
import com.sundroid.sundroid.viewmodel.SundroidViewModel
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
    private val authViewModel: PracticeViewModel by viewModels()
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class,
        ExperimentalLayoutApi::class
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            val viewModel: SundroidViewModel = viewModel()
            val drawerState = rememberDrawerState(DrawerValue.Closed)
            val scope = rememberCoroutineScope()

            SundroidTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),

                ) {
                    val navController = rememberAnimatedNavController()
                    val bottomNavigationItems = listOf(
                        Screen.Home,
                        Screen.Jobs,
                        Screen.Staff
                    )
                    val colors = listOf(
                        Color(0xFFffd7d7.toInt()),
                        Color(0xFFffe9d6.toInt()),
                        Color(0xFFfffbd0.toInt()),
                        Color(0xFFe3ffd9.toInt()),
                        Color(0xFFd0fff8.toInt())
                    )





                    val items = listOf(
                        "Item 1",
                        "Item 2",
                        "Item 3"
                    )



                  ModalNavigationDrawer(drawerContent = {   ModalDrawerSheet(



                      content = {
                          Text("Option 1")
                          Text("Option 2")
                          Text("Option 3")
                          Text("Option 4")
                          Text("Option 5")

                      }
                      )},
                      drawerState = drawerState,


                    content =
                    {
                        Scaffold(

                            topBar = {

                                if(currentRoute(navController = navController)!="splash_screen")
                                    CenterAlignedTopAppBar(
                                        modifier = Modifier.background(MaterialTheme.colorScheme.secondary),
                                        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = MaterialTheme.colorScheme.secondary, titleContentColor = Color.White, actionIconContentColor = Color.White, navigationIconContentColor = Color.White),
                                        title = {

                                            Text(
                                                viewModel.appBarTitle,
                                                maxLines = 1,
                                                overflow = TextOverflow.Ellipsis
                                            )
                                        },
                                        navigationIcon = {
                                            IconButton(onClick = {scope.launch {
                                                drawerState.open()
                                            }}) {
                                                Icon(
                                                    imageVector = Icons.Filled.Menu,
                                                    contentDescription = "Localized description"
                                                )
                                            }
                                        },
                                        actions = {
                                            IconButton(onClick = {  }) {
                                                Icon(
                                                    imageVector = Icons.Filled.Favorite,
                                                    contentDescription = "Localized description"
                                                )
                                            }
                                        }
                                    )
                            },

                            bottomBar = {
                                if(currentRoute(navController = navController)!="splash_screen")
                                    AppBottomNavigation(navController = navController, items = bottomNavigationItems)
                            },
                            content = { innerPadding ->



                                Box(modifier =
                                Modifier
                                    .fillMaxSize()
                                    .padding(innerPadding)

                                ){
                                    Navigation(
                                        navController = navController,
                                        viewModel = viewModel
                                    )
                                }


                            }
                        )
                    }
                  )





















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
    fun Navigation(navController: NavHostController, viewModel: SundroidViewModel) {


     


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
                viewModel.appBarTitle = stringResource(id = R.string.jobs)
                JobScreen()
            }
            composable(getString(R.string.staff_screen_route),  enterTransition = {  slideInHorizontally (animationSpec = tween(200)) },
                exitTransition = { slideOutHorizontally(animationSpec = tween(500)) }) {
                viewModel.appBarTitle  = stringResource(id = R.string.staff)
                StaffScreen()
            }
            composable(getString(R.string.dashboard_route),  enterTransition = {  slideInHorizontally (animationSpec = tween(200)) },
                exitTransition = { slideOutHorizontally(animationSpec = tween(500)) }) {
                viewModel.appBarTitle = stringResource(id = R.string.home)
                DashBoard()
            }
        }
    }
    @Composable
    public fun currentRoute(navController: NavHostController): String? {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        return navBackStackEntry?.destination?.route 
    }
}


