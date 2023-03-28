package com.sundroid.sundroid.custom_composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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


@Composable
fun JobList(
    @PreviewParameter(MyListProvider::class) jobs: List<Job>,
    viewModel: SundroidViewModel
) {
    LazyColumn {
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

    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .clickable(onClick = onClick)

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
                    Text(
                        text = it,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                job.description?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
                Row() {
                    StatusView(R.drawable.ic_sync_status, job.syncStatus)
                    StatusView(R.drawable.ic_payment_status, job.paymentStatus)
                    StatusView(R.drawable.ic_done, job.doneStatus)
                    StatusView(R.drawable.ic_delivered, job.deliveredStatus)

                }
            }

            Column() {
                val modifier = Modifier
                    .padding(0.dp, 16.dp, 16.dp, 0.dp)
                    .width(100.dp)

                Image(
                    painter = painterResource(id = R.drawable.ic_jobs2),
                    contentDescription = "Hello",
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
                    modifier = modifier,
                    alignment = Alignment.TopEnd

                )
                formatCurrency(job.amount.toString())?.let {
                    Text(
                        text = it,
                        modifier = modifier.width(100.dp),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }
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
            .padding(5.dp)
            .width(45.dp)
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

fun formatCurrency(amount: String): String? {
    var amount = amount
    amount = try {
        // The comma in the format specifier does the trick
        "₦" + String.format("%,.2f", amount.toFloat())
    } catch (e: NumberFormatException) {
        e.printStackTrace()
        return amount
    }
    return amount
}
