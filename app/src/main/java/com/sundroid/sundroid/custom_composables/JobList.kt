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
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
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
        viewModel.jobFormState.convertToFormState(job)
        viewModel.showBottomSheet()
    }
    val tilt = FontFamily(
        Font(R.font.tilt_neon),
        Font(R.font.tilt_warp, FontWeight.Bold),
        Font(R.font.tilt_warp, FontWeight.Bold, FontStyle.Italic),
    )
    val kanit = FontFamily(
        Font(R.font.kanit_extrabold),
        Font(R.font.kanit_bold),
        Font(R.font.kanit_medium),
    )

    Card(
        modifier = modifier
            .padding(5.dp)
            .fillMaxWidth()
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(defaultElevation = 9.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .padding(start = 16.dp,end=16.dp)
            ) {
                job.customerName?.let {
                    Surface(
                        color = Color.Transparent,
                        tonalElevation = 8.dp,
                        modifier = Modifier.width(250.dp)
                    ) {
                        SundroidTextKanitBold(
                            text = it,
                        )
                    }

                }
                Spacer(modifier = Modifier.height(8.dp))
                job.description?.let {
                    Surface(
                        color = Color.Transparent,
                        tonalElevation = 8.dp,
                        modifier = Modifier.width(250.dp)
                    ) {
                        SundroidText(
                            text = it,
                        )
                    }
                }

            }

            Column() {
                SundroidTextAmount(
                    text = job.amount.toString()
                )


            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth().padding(start =  16.dp, end = 16.dp, bottom = 10.dp)
                .height(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(Modifier.weight(5f)){
                StatusView(R.drawable.ic_sync_status, job.syncStatus, modifier = Modifier)
                StatusView(R.drawable.ic_payment_status, job.paymentStatus, modifier = Modifier)
                StatusView(R.drawable.ic_done, job.doneStatus, modifier = Modifier)
                StatusView(R.drawable.ic_delivered, job.deliveredStatus, modifier = Modifier)

            }
            Spacer(modifier = Modifier.weight(8f))


            DisplayLocalDate(modifier = Modifier.weight(8f), job.timeReceived)
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
            .width(15.dp)
            .padding(1.dp)
    )
}

class MyListProvider : PreviewParameterProvider<ArrayList<Job>> {
    override val values: Sequence<ArrayList<Job>> = sequenceOf(
        Job.getSampleJobs()
    )
}

fun getColorFilter(state: Boolean): ColorFilter {
    return if (state) ColorFilter.tint(Color.Green) else ColorFilter.tint(Color.Gray)
}
