package com.sundroid.sundroid.models

import androidx.compose.runtime.mutableStateOf
import com.sundroid.sundroid.data.local.dao.database_models.Job

class JobFormState(){










     var customerName = mutableStateOf("")
     var customerEmail = mutableStateOf("")
     var timeReceived = mutableStateOf("")
     var timeDelivered =  mutableStateOf("")
     var description =mutableStateOf("")
     var paymentStatus = mutableStateOf(false)
     var syncStatus = mutableStateOf(false)
     var doneStatus = mutableStateOf(false)
     var deliveredStatus = mutableStateOf(false)
     var amount = mutableStateOf(0)
     val jobId = mutableStateOf(-1)


     fun clearForm(){
          customerName.value = ""
          customerEmail.value = ""
          timeReceived.value = ""
          timeDelivered.value = ""
          description.value = ""
          paymentStatus.value = false
          syncStatus.value = false
          doneStatus.value = false
          deliveredStatus.value = false
          jobId.value =-1
          amount.value=0

     }
     fun convertToFormState(job: Job){
          clearForm()
          job.apply {
              this@JobFormState.customerName.value = customerName
              this@JobFormState.customerEmail.value = customerEmail
              this@JobFormState.amount.value = amount
              this@JobFormState.description.value = description
              this@JobFormState.deliveredStatus.value = deliveredStatus
              this@JobFormState.doneStatus.value = doneStatus
              this@JobFormState.paymentStatus.value = paymentStatus
              this@JobFormState.syncStatus.value = syncStatus
              this@JobFormState.timeDelivered.value = timeDelivered
              this@JobFormState.timeReceived.value = timeReceived
               this@JobFormState.jobId.value = jobId
          }
     }

     fun getJobFromFormState(): Job {
          var job = Job(
               customerName = customerName.value,
               customerEmail = customerEmail.value,
               amount = amount.value,
               description = description.value,
               deliveredStatus = deliveredStatus.value,
               doneStatus = doneStatus.value,
               paymentStatus =  paymentStatus.value,
               syncStatus = syncStatus.value,
               timeDelivered = timeDelivered.value,
               timeReceived = timeReceived.value

          )
          if(jobId.value>=0){
               job.jobId = jobId.value
          }
          return job;

     }
}

