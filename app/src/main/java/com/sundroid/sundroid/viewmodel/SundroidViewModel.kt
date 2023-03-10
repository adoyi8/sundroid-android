package com.sundroid.sundroid.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel










class SundroidViewModel : ViewModel() {
    var appBarTitle by mutableStateOf("Sundroid")
}