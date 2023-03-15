package com.sundroid.sundroid.custom_composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.sundroid.sundroid.R
import com.sundroid.sundroid.models.Job




@Preview
@Composable
fun JobList(@PreviewParameter(MyListProvider::class) jobs: ArrayList<Job>) {
    LazyColumn {
        items(jobs) { job ->
            MyCard(job)
        }
    }
}







@Composable
fun MyCard(job: Job) {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Row( modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween) {
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
            }
            Image(painter = painterResource(id = R.drawable.ic_jobs2), contentDescription = "Hello", colorFilter =  ColorFilter.tint(MaterialTheme.colorScheme.primary),modifier = Modifier
                .padding(16.dp).width(90.dp))
        }

    }
}
@Preview
@Composable
fun MyComposablePreview() {
    JobList(jobs = ArrayList())
}
class MyListProvider : PreviewParameterProvider<ArrayList<Job>> {
    override val values: Sequence<ArrayList<Job>> = sequenceOf(
        Job.getSampleJobs()
    )
}
