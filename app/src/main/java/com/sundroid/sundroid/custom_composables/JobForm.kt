package com.sundroid.sundroid.custom_composables



import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sundroid.sundroid.viewmodel.SundroidViewModel










@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun AddJobForm(
    viewModel: SundroidViewModel

) {











    AnimatedVisibility(
        visible = true,
        enter = slideInVertically(),
        exit = slideOutVertically()
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            Spacer(modifier = Modifier.height(30.dp))
            SundroidTextHeader(text = "Add Job")
            SundroidTextField("Customer Name", onChange = {
                viewModel.jobFormState.customerName.value = it
            }, viewModel = viewModel, value = viewModel.jobFormState.customerName.value)
            SundroidTextField("Customer Email", onChange = {
                viewModel.jobFormState.customerEmail.value = it
            }, viewModel = viewModel, value = viewModel.jobFormState.customerEmail.value)
            SundroidTextArea("Job Description", onChange = {
                viewModel.jobFormState.description.value = it
            }, viewModel = viewModel, value = viewModel.jobFormState.description.value)

            SundroidTextFieldAmount("Amount", onChange = {

                viewModel.jobFormState.amount.value = it.toInt()
            }, viewModel = viewModel, value = viewModel.jobFormState.amount.value)
            SundroidButton(onClick = { viewModel.addJob() }, text = "Add")
        }
    }
}

@Composable
fun UpdateJobForm(
    viewModel: SundroidViewModel

) {












    Column( modifier = Modifier.padding(10.dp)) {
        Spacer(modifier = Modifier.height(30.dp))
        SundroidTextHeader(text = "Update Job")
        SundroidTextField("Customer Name", onChange = {
            println("arya $it")
            viewModel.jobFormState.customerName.value = it
        }, viewModel = viewModel, value = viewModel.jobFormState.customerName.value)
        SundroidTextField("Customer Email", onChange = {
            viewModel.jobFormState.customerEmail.value = it
        }, viewModel = viewModel, value = viewModel.jobFormState.customerEmail.value)
        SundroidTextArea("Job Description", onChange = {
            viewModel.jobFormState.description.value = it
        }, viewModel = viewModel, value = viewModel.jobFormState.description.value)

        SundroidTextFieldAmount("Amount", onChange = {

            viewModel.jobFormState.amount.value = it.toInt()
        }, viewModel = viewModel, value = viewModel.jobFormState.amount.value)
        SundroidButton(onClick = { viewModel.updateJob() }, text = "Update")
    }
}



