package com.sundroid.sundroid.screens



import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.sundroid.sundroid.models.Screen


@Composable
fun AppBottomNavigation(navController: NavHostController,
                        items: List<Screen>)

     {

         NavigationBar(containerColor = MaterialTheme.colorScheme.secondary, contentColor = Color.White) {
             val currentRoute = currentRoute(navController)
             items.forEach { screen ->
                 NavigationBarItem(
                     icon = {  Icon(Icons.Filled.Home, "Home", tint = Color.White)},
                     label = { Text(stringResource(id = screen.resourceId), color = Color.White) },
                     selected = currentRoute == screen.route,
                     // This hides the title for the unselected items
                     onClick = {
                         // This if check gives us a "singleTop" behavior where we do not create a
                         // second instance of the composable if we are already on that destination
                         if (currentRoute != screen.route) {
                             navController.navigate(screen.route)
                         }
                     },

                 )
             }
         }
}
@Composable
private fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}