package com.sundroid.sundroid.screens



import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.sundroid.sundroid.R
import com.sundroid.sundroid.models.Screen


@Composable
fun AppBottomNavigation(navController: NavHostController,
                        items: List<Screen>)

     {

         NavigationBar(containerColor = MaterialTheme.colorScheme.secondary) {
             val currentRoute = currentRoute(navController)
             items.forEach { screen ->
                 NavigationBarItem(
                     icon = { R.drawable.ic_home},
                     label = { Text(stringResource(id = screen.resourceId)) },
                     selected = currentRoute == screen.route,
                     // This hides the title for the unselected items
                     onClick = {
                         // This if check gives us a "singleTop" behavior where we do not create a
                         // second instance of the composable if we are already on that destination
                         if (currentRoute != screen.route) {
                             navController.navigate(screen.route)
                         }
                     }
                 )
             }
         }
}
@Composable
private fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}