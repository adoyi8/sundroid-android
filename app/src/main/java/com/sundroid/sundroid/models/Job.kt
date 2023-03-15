package com.sundroid.sundroid.models

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate










data class Job(
    val customerName: String?,
    val customerEmail: String?,
    val timeReceived: LocalDate,
    val timeDelivered: LocalDate?,
    val description : String?,
    val jobId: Int,
    val paymentStatus: Boolean?,
    val syncStatus: Boolean?,
    val doneStatus: Boolean?,
    val deliveredStatus: Boolean?,
    val amount: Long?,

){
    companion object {
        @RequiresApi(Build.VERSION_CODES.O)
        fun getSampleJobs(): ArrayList<Job> {
            val jobs = arrayListOf<Job>()
            var job = Job(
                customerName = "Sunday Adegbe",
                customerEmail = "adegbesundayadoyi@gmail.com",
                timeReceived = LocalDate.now(),
                timeDelivered = LocalDate.now(),
                description = "Sunday Washing and Iron",
                jobId = 1,
                paymentStatus = false,
                syncStatus = false,
                doneStatus = false,
                deliveredStatus = false,
                amount = 2000
            )
            jobs.add(job)
            return jobs
        }
    }
}
