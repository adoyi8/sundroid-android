package com.sundroid.sundroid


import CircularImage
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.sundroid.sundroid.custom_composables.*
import com.sundroid.sundroid.data.AuthScreen
import com.sundroid.sundroid.google_auth.getGoogleSignInClient
import com.sundroid.sundroid.models.BottomSheetAction
import com.sundroid.sundroid.models.DrawerItem
import com.sundroid.sundroid.models.Screen
import com.sundroid.sundroid.screens.*
import com.sundroid.sundroid.ui.theme.SundroidTheme
import com.sundroid.sundroid.ui.theme.screens.SplashScreen
import com.sundroid.sundroid.viewmodel.SundroidViewModel
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {

    @OptIn(
        ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class,
        ExperimentalAnimationApi::class
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent() {
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
                        Screen.StaffScreen
                    )
                    val items = listOf(
                        DrawerItem(R.drawable.ic_shop, "My Shops"),
                        DrawerItem(R.drawable.settings, "Settings"),
                        DrawerItem(R.drawable.logout, "Log Out"),
                    )
                    val selectedItem = remember { mutableStateOf(items[0]) }
                    val openDialog = remember { mutableStateOf(false) }


                    if (openDialog.value) {

                        SundroidAlertDialog(openDialog, onYesClick = {
                            openDialog.value = false
                            viewModel.logOut()
                            println("Blessing 4 Hello")
                            getGoogleSignInClient(this).signOut()
                            navController.navigate("auth_screen") {
                                popUpTo("splash_screen") { inclusive = true }

                            }

                        }, icon = R.drawable.logout)

                    }
                    ModalNavigationDrawer(
                        modifier = Modifier.width(50.dp),
                        drawerContent = {
                            ModalDrawerSheet {

                                Box(
                                    Modifier
                                        .width(300.dp) // Set the width of the content here
                                ) {


                                    Column(modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)) {
                                        Spacer(modifier = Modifier.height(25.dp))
                                        SundroidTextKanitBold(
                                            text = "Sundroid"
                                        )
                                        Spacer(modifier = Modifier.height(25.dp))
                                        items.forEach { item ->
                                            NavigationDrawerItem(
                                                icon = {
                                                    Image(
                                                        painter = painterResource(id = item.icon),
                                                        contentDescription = null
                                                    )
                                                },
                                                label = { SundroidText(item.title) },
                                                selected = item == selectedItem.value,
                                                onClick = {
                                                    scope.launch { drawerState.close() }
                                                    selectedItem.value = item
                                                    if (item.title == "Log Out") {
                                                        openDialog.value = true
                                                    } else if (item.title == "My Shops") {
                                                        openDialog.value = false
                                                        navController.navigate(getString(R.string.shop_screen)) {
                                                            popUpTo("splash_screen") {
                                                                inclusive = true
                                                            }

                                                        }
                                                    }
                                                },

                                                )
                                        }


                                    }


                                }
                            }
                        },
                        drawerState = drawerState,
                        content = {
                            ModalBottomSheetLayout(
                                sheetState = viewModel.bottomSheetState.value,
                                sheetBackgroundColor = MaterialTheme.colorScheme.surface,
                                sheetContentColor = contentColorFor(backgroundColor = MaterialTheme.colorScheme.surface),
                                sheetContent = { SundroidBottomSheetContent(viewModel = viewModel) },
                                content = {
                                    Scaffold(
                                        floatingActionButton = {
                                            if (currentRoute(navController = navController) == stringResource(
                                                    id = R.string.job_screen_route
                                                )
                                            ) {
                                                var onClick: () -> Unit = {
                                                    viewModel.bottomSheetAction.value =
                                                        BottomSheetAction.ADD_JOB

                                                    scope.launch {
                                                        viewModel.showBottomSheet()
                                                    }
                                                }
                                                SundroidFAB(
                                                    R.drawable.ic_add,
                                                    "Add Job",
                                                    onclick = onClick
                                                )
                                            } else if (currentRoute(navController = navController) == stringResource(
                                                    id = R.string.shop_screen
                                                )
                                            ) {
                                                var onClick: () -> Unit = {
                                                    viewModel.bottomSheetAction.value =
                                                        BottomSheetAction.ADD_SHOP

                                                    scope.launch {
                                                        viewModel.showBottomSheet()
                                                    }
                                                }
                                                SundroidFAB(
                                                    R.drawable.ic_add,
                                                    "Register Shop",
                                                    onclick = onClick
                                                )
                                            } else if (currentRoute(navController = navController) == stringResource(
                                                    id = R.string.staff_screen_route
                                                )
                                            ) {
                                                var onClick: () -> Unit = {
                                                    viewModel.bottomSheetAction.value =
                                                        BottomSheetAction.ADD_STAFF

                                                    scope.launch {
                                                        viewModel.showBottomSheet()
                                                    }
                                                }
                                                SundroidFAB(
                                                    R.drawable.ic_add,
                                                    "Register Staff",
                                                    onclick = onClick
                                                )
                                            }
                                        },

                                        topBar = {
                                            if (currentRoute(navController = navController) != stringResource(
                                                    id = R.string.splash_screen_route
                                                ) && currentRoute(
                                                    navController = navController
                                                ) != stringResource(id = R.string.auth_screen_route)
                                            ) {
                                                CenterAlignedTopAppBar(
                                                    title = {

                                                        SundroidText(
                                                            viewModel.appBarTitle,
                                                        )
                                                    },
                                                    navigationIcon = {
                                                        IconButton(onClick = {
                                                            scope.launch {
                                                                drawerState.open()
                                                            }
                                                        }) {
                                                            Icon(
                                                                imageVector = Icons.Filled.Menu,
                                                                contentDescription = "Localized description"
                                                            )
                                                        }
                                                    },
                                                    actions = {
                                                        CircularImage(viewModel.currentUser.value.photoUrl)
                                                        Spacer(modifier = Modifier.width(10.dp))
                                                    }
                                                )
                                            } else if (currentRoute(navController = navController) == stringResource(
                                                    id = R.string.auth_screen_route
                                                )
                                            ) {
                                                CenterAlignedTopAppBar(
                                                    title = {

                                                        SundroidText(
                                                            viewModel.appBarTitle,
                                                        )
                                                    },

                                                    )
                                            }
                                        },

                                        bottomBar = {
                                            if (currentRoute(navController = navController) != stringResource(
                                                    id = R.string.splash_screen_route
                                                ) && currentRoute(navController = navController) != stringResource(
                                                    id = R.string.auth_screen_route
                                                )
                                            )
                                                AppBottomNavigation(
                                                    navController = navController,
                                                    items = bottomNavigationItems
                                                )
                                        },
                                        content = { innerPadding ->


                                            Box(
                                                modifier =
                                                Modifier
                                                    .fillMaxSize()
                                                    .padding(innerPadding)

                                            ) {
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


        AnimatedNavHost(
            navController = navController,
            startDestination = getString(R.string.splash_screen_route)
        ) {
            composable(getString(R.string.splash_screen_route),
                enterTransition = { slideInHorizontally(animationSpec = tween(200)) },
                exitTransition = { slideOutHorizontally(animationSpec = tween(500)) })

            {
                SplashScreen(navController = navController, viewModel = viewModel)

            }
            // Main Screen
            composable(getString(R.string.job_screen_route),
                enterTransition = { slideInHorizontally(animationSpec = tween(200)) },
                exitTransition = { slideOutHorizontally(animationSpec = tween(500)) }) {
                viewModel.appBarTitle = stringResource(id = R.string.jobs)
                JobScreen(viewModel = viewModel, navController = navController)
            }
            composable(getString(R.string.staff_screen_route),
                enterTransition = { slideInHorizontally(animationSpec = tween(200)) },
                exitTransition = { slideOutHorizontally(animationSpec = tween(500)) }) {
                viewModel.appBarTitle = stringResource(id = R.string.staff)
                StaffScreen(viewModel = viewModel, navController = navController)
            }
            composable(getString(R.string.dashboard_route),
                enterTransition = { slideInHorizontally(animationSpec = tween(200)) },
                exitTransition = { slideOutHorizontally(animationSpec = tween(500)) }) {
                viewModel.appBarTitle = stringResource(id = R.string.home)
                DashBoard(viewModel = viewModel, navController = navController)
            }
            composable(getString(R.string.auth_screen_route),
                enterTransition = { slideInHorizontally(animationSpec = tween(200)) },
                exitTransition = { slideOutHorizontally(animationSpec = tween(500)) }) {
                viewModel.appBarTitle = stringResource(id = R.string.auth)
                AuthScreen(navController = navController, viewModel = viewModel)
            }
            composable(getString(R.string.add_job_screen_route),
                enterTransition = { slideInHorizontally(animationSpec = tween(200)) },
                exitTransition = { slideOutHorizontally(animationSpec = tween(500)) }) {
                viewModel.appBarTitle = stringResource(id = R.string.add_job)
                AddJobForm(viewModel = viewModel)
            }
            composable(getString(R.string.shop_screen),
                enterTransition = { slideInHorizontally(animationSpec = tween(200)) },
                exitTransition = { slideOutHorizontally(animationSpec = tween(500)) }) {
                viewModel.appBarTitle = stringResource(id = R.string.my_shops)
                ShopScreen(viewModel = viewModel, navController = navController)
            }
        }
    }

    @Composable
    public fun currentRoute(navController: NavHostController): String? {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        return navBackStackEntry?.destination?.route
    }
}


