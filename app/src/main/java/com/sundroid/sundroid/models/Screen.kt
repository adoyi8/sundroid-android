package com.sundroid.sundroid.models


import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.sundroid.sundroid.R












sealed class Screen(val route: String, @StringRes val resourceId: Int, val icon: ImageVector){
    object Home : Screen("dashboard_screen", R.string.home, Icons.Filled.Home)
    object Staff : Screen("staff_screen", R.string.staff, Icons.Filled.Person)
    object Jobs : Screen("jobs_screen", R.string.jobs, Icons.Filled.List)
}
