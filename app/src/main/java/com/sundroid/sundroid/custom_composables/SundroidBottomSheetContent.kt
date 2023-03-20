package com.sundroid.sundroid.custom_composables

import androidx.compose.runtime.Composable
import com.sundroid.sundroid.models.BottomSheetAction
import com.sundroid.sundroid.viewmodel.SundroidViewModel

@Composable
fun SundroidBottomSheetContent(viewModel: SundroidViewModel){









    when(viewModel.bottomSheetAction.value){
        BottomSheetAction.ADD_JOB->{
             AddJobForm(viewModel = viewModel)
        }
        else ->{

        }
    }



}