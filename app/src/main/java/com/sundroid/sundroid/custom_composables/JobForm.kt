package com.sundroid.sundroid.custom_composables


import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.*
import androidx.compose.ui.text.style.*
import androidx.compose.ui.unit.*
import com.sundroid.sundroid.viewmodel.SundroidViewModel










@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddJobForm(
    viewModel: SundroidViewModel

) {

    Column( modifier = Modifier.padding(10.dp)) {
        
        Spacer(modifier = Modifier.height(30.dp))
         SundroidTextHeader(text = "Add Job")
        SundroidTextField("Customer Name", onChange = {
            viewModel.formState.firstName.value = it
        }, viewModel = viewModel, value = viewModel.formState.firstName.value)
        SundroidTextField("Customer Email", onChange = {
            viewModel.formState.email.value = it
        }, viewModel = viewModel, value = viewModel.formState.email.value)

        SundroidButton(onClick = { viewModel.addJob() }, text = "Add")

    }
}



