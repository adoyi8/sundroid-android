package com.sundroid.sundroid.models

import java.time.LocalDate










data class Job(
    val customerName: String?,
    val customerEmail: String?,
    val timeReceived: LocalDate,
    val timeDelivered: LocalDate?,
    val description : String?,
    val jobId: Int,
    val paymentStatus: Boolean = false,
    val syncStatus: Boolean = false,
    val doneStatus: Boolean = false,
    val deliveredStatus: Boolean = false,
    val amount: Long?,

){
    companion object {

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
             job = Job(
                customerName = "Daniel Adegbe",
                customerEmail = "adegbesundayadoyi@gmail.com",
                timeReceived = LocalDate.now(),
                timeDelivered = LocalDate.now(),
                description = "Daniel Washing Only",
                jobId = 2,
                paymentStatus = true,
                syncStatus = true,
                doneStatus = false,
                deliveredStatus = false,
                amount = 2000
            )
            return jobs
        }
    }
}
