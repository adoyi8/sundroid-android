package com.sundroid.sundroid.custom_composables

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.sundroid.sundroid.R
import com.sundroid.sundroid.data.local.dao.database_models.Job
import com.sundroid.sundroid.models.BottomSheetAction
import com.sundroid.sundroid.viewmodel.SundroidViewModel


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun JobList(
    @PreviewParameter(MyListProvider::class) jobs: List<Job>,
    viewModel: SundroidViewModel
) {
    Box() {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(
                space = 0.dp,
                alignment = Alignment.Top
            ),
        ) {
            items(jobs, key = { it.jobId }) { job ->
                MyCard(
                    job, viewModel, modifier = Modifier.animateItemPlacement(
                        animationSpec = tween(
                            durationMillis = 2000,
                            easing = LinearOutSlowInEasing,
                        )
                    )
                )
            }
        }
    }
}


@Composable
fun MyCard(job: Job, viewModel: SundroidViewModel, modifier: Modifier) {
    var onClick: () -> Unit = {
        viewModel.bottomSheetAction.value = BottomSheetAction.UPDATE_JOB
        viewModel.currentJob.value = job
        viewModel.jobFormState.convertToFormState(job)
        viewModel.showBottomSheet()
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
    ) {
   Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {


        Row(
            modifier = Modifier
                .fillMaxWidth()
                ,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(Modifier.widthIn(max = 200.dp)) {
                SundroidTextHeader(text = job.customerName, modifier = Modifier.align(Alignment.CenterStart), align = TextAlign.Left)
            }
            Box() {
                com.sundroid.sundroid.custom_composables.SundroidTextHeader(
                    text = formatCurrency(
                        job.amount.toString()
                    ), align  = TextAlign.Right, modifier = Modifier.align(Alignment.CenterEnd)
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
            ,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
            ) {
                job.description?.let {
                    Surface(
                        color = Color.Transparent,
                        tonalElevation = 8.dp,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        SundroidText(
                            text = it,
                        )
                    }
                }

            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                ,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row() {
                StatusView(R.drawable.ic_sync_status, job.syncStatus, modifier = Modifier)
                StatusView(R.drawable.ic_payment_status, job.paymentStatus, modifier = Modifier)
                StatusView(R.drawable.ic_done, job.doneStatus, modifier = Modifier)
                StatusView(R.drawable.ic_delivered, job.deliveredStatus, modifier = Modifier)
            }

             Box() {
                 DisplayLocalDate(modifier = Modifier.align(Alignment.CenterEnd), job.timeReceived)
             }
        }
    }
}
}


@Composable
fun StatusView(icon: Int, state: Boolean, modifier: Modifier) {
    Image(
        painter = painterResource(id = icon),
        contentDescription = "Hello",
        colorFilter = getColorFilter(state),
        modifier = modifier
            .width(25.dp)
            .padding(1.dp)
    )
}

class MyListProvider : PreviewParameterProvider<ArrayList<Job>> {
    override val values: Sequence<ArrayList<Job>> = sequenceOf(
        Job.getSampleJobs()
    )
}

fun getColorFilter(state: Boolean): ColorFilter {
    return if (state) ColorFilter.tint(Color(0xFF4FB6EC)) else ColorFilter.tint(Color.Gray)
}
