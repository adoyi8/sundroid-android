package com.sundroid.sundroid.custom_composables

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import com.sundroid.sundroid.models.BottomSheetAction
import com.sundroid.sundroid.viewmodel.SundroidViewModel










@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SundroidBottomSheetContent(viewModel: SundroidViewModel){
    when(viewModel.bottomSheetAction.value){
        BottomSheetAction.ADD_JOB->{

                AddJobForm(viewModel = viewModel)

        }
        BottomSheetAction.UPDATE_JOB->{
            UpdateJobForm(viewModel = viewModel)
        }
        BottomSheetAction.ADD_SHOP->{
            AddShopForm(viewModel = viewModel)
        }
        BottomSheetAction.VIEW_SHOP->{
            UpdateShopForm(viewModel = viewModel)
        }
        BottomSheetAction.ADD_STAFF->{
            AddStaffForm(viewModel = viewModel)
        }
        BottomSheetAction.UPDATE_STAFF ->{
            UpdateStaffForm(viewModel = viewModel)
        }
        else ->{

        }
    }



}