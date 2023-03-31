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
fun AddShopForm(
    viewModel: SundroidViewModel

) {

    Column( modifier = Modifier.padding(10.dp)) {
        Spacer(modifier = Modifier.height(30.dp))
         SundroidTextHeader(text = "Register Shop")
        SundroidTextField("Shop Name", onChange = {
            viewModel.shopFormState.name.value = it
        }, viewModel = viewModel, value = viewModel.shopFormState.name.value, label = "Shop Name")
        SundroidTextField("Shop Address", onChange = {
            viewModel.shopFormState.address.value = it
        }, viewModel = viewModel, value = viewModel.shopFormState.address.value, label = "Address")
        SundroidTextField("Phone", onChange = {
            viewModel.shopFormState.phone.value = it
        }, viewModel = viewModel, value = viewModel.shopFormState.phone.value, label = "Phone")
        SundroidButton(onClick = { viewModel.addShop() }, text = "Add Shop")
    }
}

@Composable
fun UpdateShopForm(
    viewModel: SundroidViewModel

) {












    Column( modifier = Modifier.padding(10.dp)) {
        Spacer(modifier = Modifier.height(30.dp))
        SundroidTextHeader(text = viewModel.shopFormState.name.value)
        SundroidTextField("Shop Name", onChange = {
            viewModel.shopFormState.name.value = it
        }, viewModel = viewModel, value = viewModel.shopFormState.name.value, label = "Name")
        SundroidTextField("Shop Address", onChange = {
            viewModel.shopFormState.address.value = it
        }, viewModel = viewModel, value = viewModel.shopFormState.address.value, label = "Address")
        SundroidTextField("Phone", onChange = {
            viewModel.shopFormState.phone.value = it
        }, viewModel = viewModel, value = viewModel.shopFormState.phone.value, label = "Phone")
      //  SundroidButton(onClick = { viewModel.addJob() }, text = "Update Shop")
    }
}



