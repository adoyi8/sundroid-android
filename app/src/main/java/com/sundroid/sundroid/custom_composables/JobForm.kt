package com.sundroid.sundroid.custom_composables


import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Switch
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sundroid.sundroid.viewmodel.SundroidViewModel





@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun AddJobForm(
    viewModel: SundroidViewModel
) {
    Column(modifier = Modifier.padding(10.dp)) {
        Spacer(modifier = Modifier.height(30.dp))
        SundroidTextHeader(text = "Add Job")
        SundroidTextField(
            "Customer Name",
            onChange = {
                viewModel.jobFormState.customerName.value = it
            },
            viewModel = viewModel,
            value = viewModel.jobFormState.customerName.value,
            label = "Customer Name"
        )
        SundroidTextArea(
            "Description",
            onChange = {
                viewModel.jobFormState.description.value = it
            },
            viewModel = viewModel,
            value = viewModel.jobFormState.description.value,
            label = "Description"
        )
        SundroidTextFieldAmount("Amount", onChange = {
            viewModel.jobFormState.amount.value = it.toInt()
        }, viewModel = viewModel, value = viewModel.jobFormState.amount.value, label = "Amount")
        SundroidButtonLarge(onClick = { viewModel.addJob() }, text = "Add")
    }

}

@Composable
fun UpdateJobForm(
    viewModel: SundroidViewModel

) {
    Column(modifier = Modifier.padding(10.dp)) {
        Row() {
            Box(Modifier.weight(5f)) {
                SundroidTextHeader(text = viewModel.jobFormState.customerName.value)
            }
            Spacer(modifier = Modifier.weight(5f))
            Box(Modifier.weight(5f)) {
                com.sundroid.sundroid.custom_composables.SundroidTextHeader(text = formatCurrency(viewModel.jobFormState.amount.value.toString()))
            }
        }
        SundroidText(text = viewModel.jobFormState.description.value)
        Divider(color =MaterialTheme.colorScheme.onSurface, thickness = 1.dp, modifier = Modifier.padding(10.dp))
        Row() {
            Box(Modifier.weight(5f)) {
                SundroidText(text = "Paid")
            }
            Spacer(modifier = Modifier.weight(5f))
            Switch(checked = viewModel.jobFormState.paymentStatus.value,
                onCheckedChange = { viewModel.jobFormState.paymentStatus.value = it }, enabled = !(viewModel.currentJob.value.paymentStatus))
        }
        Row() {
            Box(Modifier.weight(5f)) {
                SundroidText(text = "Done")
            }
            Spacer(modifier = Modifier.weight(5f))
            Switch(checked = viewModel.jobFormState.doneStatus.value,
                onCheckedChange = { viewModel.jobFormState.doneStatus.value = it }, enabled = !(viewModel.currentJob.value.doneStatus))
        }
        Row() {
            Box(Modifier.weight(5f)) {
                SundroidText(text = "Delivered")
            }
            Spacer(modifier = Modifier.weight(5f))
            Switch(checked = viewModel.jobFormState.deliveredStatus.value,
                onCheckedChange = { viewModel.jobFormState.deliveredStatus.value = it }, enabled = !(viewModel.currentJob.value.deliveredStatus))
        }
        }
        SundroidButtonLarge(onClick = { viewModel.updateJob() }, text = "Update")
    }




