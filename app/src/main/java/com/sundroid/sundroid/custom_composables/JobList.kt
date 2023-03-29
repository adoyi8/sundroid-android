package com.sundroid.sundroid.custom_composables

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




@Composable
fun JobList(
    @PreviewParameter(MyListProvider::class) jobs: List<Job>,
    viewModel: SundroidViewModel
) {
    LazyColumn(verticalArrangement = Arrangement.spacedBy(space = 0.dp, alignment =  Alignment.Top)) {
        items(jobs) { job ->
            MyCard(job, viewModel)
        }
    }
}


@Composable
fun MyCard(job: Job, viewModel: SundroidViewModel) {
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
        modifier = Modifier
            .padding(7.dp)
            .fillMaxWidth()
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(defaultElevation = 11.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
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
        Row(modifier = Modifier
            .width(370.dp)
            .fillMaxWidth().height(20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween) {
            StatusView(R.drawable.ic_sync_status, job.syncStatus)
            StatusView(R.drawable.ic_payment_status, job.paymentStatus)
            StatusView(R.drawable.ic_done, job.doneStatus)
            StatusView(R.drawable.ic_delivered, job.deliveredStatus)
            Spacer(modifier = Modifier.width(100.dp))
            DisplayLocalDate(modifier = Modifier)


        }
        }

    }


@Composable
fun StatusView(icon: Int, state: Boolean) {
    Image(
        painter = painterResource(id = icon),
        contentDescription = "Hello",
        colorFilter = getColorFilter(state),
        modifier = Modifier
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
