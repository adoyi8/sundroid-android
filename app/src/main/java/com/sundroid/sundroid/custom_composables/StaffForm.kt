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
fun AddStaffForm(
    viewModel: SundroidViewModel

) {

    Column( modifier = Modifier.padding(10.dp)) {
        Spacer(modifier = Modifier.height(30.dp))
         SundroidTextHeader(text = "Register Staffp")
        SundroidTextField("Name", onChange = {
            viewModel.staffFormState.name.value = it
        }, viewModel = viewModel, value = viewModel.staffFormState.name.value)
        SundroidTextField("Shop Address", onChange = {
            viewModel.staffFormState.email.value = it
        }, viewModel = viewModel, value = viewModel.staffFormState.email.value)
        SundroidTextField("Phone", onChange = {
            viewModel.staffFormState.phone.value = it
        }, viewModel = viewModel, value = viewModel.staffFormState.phone.value)
        SundroidButton(onClick = { viewModel.addShop() }, text = "Add Staff")
    }
}

@Composable
fun UpdateStaffForm(
    viewModel: SundroidViewModel

) {





    Column( modifier = Modifier.padding(10.dp)) {
        Spacer(modifier = Modifier.height(30.dp))
        SundroidTextHeader(text = viewModel.staffFormState.name.value  )
        SundroidTextField("Name", onChange = {
            viewModel.staffFormState.name.value = it
        }, viewModel = viewModel, value = viewModel.staffFormState.name.value)
        SundroidTextField("Shop Address", onChange = {
            viewModel.staffFormState.email.value = it
        }, viewModel = viewModel, value = viewModel.staffFormState.email.value)
        SundroidTextField("Phone", onChange = {
            viewModel.staffFormState.phone.value = it
        }, viewModel = viewModel, value = viewModel.staffFormState.phone.value)
        SundroidButton(onClick = { viewModel.addShop() }, text = "Update Staff")
    }
}



