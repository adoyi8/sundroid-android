package com.sundroid.sundroid.models

import java.time.LocalDate










data class Job(
    var customerName: String="",
    val customerEmail: String?="",
    val timeReceived: LocalDate= LocalDate.now(),
    val timeDelivered: LocalDate?=LocalDate.now(),
    val description : String?="",
    val jobId: Int=0,
    val paymentStatus: Boolean = false,
    val syncStatus: Boolean = false,
    val doneStatus: Boolean = false,
    val deliveredStatus: Boolean = false,
    val amount: Double?=0.0,

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
                amount = 2000.0
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
                amount = 2000.0
            )
            return jobs
        }
    }
}
