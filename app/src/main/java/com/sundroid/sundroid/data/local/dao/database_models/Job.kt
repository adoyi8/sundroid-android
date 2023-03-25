package com.sundroid.sundroid.data.local.dao.database_models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter












@Entity(tableName = "job")
data class Job
@JvmOverloads constructor(@PrimaryKey(autoGenerate = true) var jobId: Int=0,
                          var customerName: String="",
                          var customerEmail: String="",
                          var timeReceived: String= LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                          var timeDelivered: String="",
                          var description : String="",
                          var paymentStatus: Boolean = false,
                          var syncStatus: Boolean = false,
                          var doneStatus: Boolean = false,
                          var deliveredStatus: Boolean = false,
                          var amount: Int=0, )
{
    companion object {
        fun getSampleJobs(): ArrayList<Job> {
            val jobs = arrayListOf<Job>()
            var job = Job(
                customerName = "Sunday Adegbe",
                customerEmail = "adegbesundayadoyi@gmail.com",
                timeReceived = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                timeDelivered = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
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
                timeReceived = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                timeDelivered = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                description = "Daniel Washing Only",
                jobId = 2,
                paymentStatus = true,
                syncStatus = true,
                doneStatus = false,
                deliveredStatus = false,
                amount = 2000
            )
            jobs.add(job)
            return jobs
        }
    }
}
