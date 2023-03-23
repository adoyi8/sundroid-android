package com.sundroid.sundroid.models

import androidx.compose.runtime.mutableStateOf

class AddJobFormState(){










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
          doneStatus.value = true
          deliveredStatus.value = false
          jobId.value =-1
          amount.value=0

     }
     fun convertToFormState(job: Job){
          clearForm()
          job.apply {
              this@AddJobFormState.customerName.value = customerName
              this@AddJobFormState.customerEmail.value = customerEmail
              this@AddJobFormState.amount.value = amount
              this@AddJobFormState.description.value = description
              this@AddJobFormState.deliveredStatus.value = deliveredStatus
              this@AddJobFormState.doneStatus.value = doneStatus
              this@AddJobFormState.paymentStatus.value = paymentStatus
              this@AddJobFormState.syncStatus.value = syncStatus
              this@AddJobFormState.timeDelivered.value = timeDelivered
              this@AddJobFormState.timeReceived.value = timeReceived
               this@AddJobFormState.jobId.value = jobId
          }
     }

     fun getJobFromFormState():Job{
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

