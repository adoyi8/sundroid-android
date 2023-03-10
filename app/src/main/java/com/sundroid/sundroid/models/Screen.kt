package com.sundroid.sundroid.models


import androidx.annotation.StringRes
import com.sundroid.sundroid.R












sealed class Screen(val route: String, @StringRes val resourceId: Int, val icon: Int){
    object Home : Screen("dashboard_screen", R.string.home, R.drawable.ic_home)
    object Staff : Screen("staff_screen", R.string.staff, R.drawable.ic_staff)
    object Jobs : Screen("jobs_screen", R.string.jobs, R.drawable.ic_jobs)
}
