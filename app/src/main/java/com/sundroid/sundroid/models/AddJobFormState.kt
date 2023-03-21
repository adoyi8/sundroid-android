package com.sundroid.sundroid.models

import androidx.compose.runtime.mutableStateOf

class AddJobFormState(){










     var firstName = mutableStateOf("")
     var email = mutableStateOf("")
     var jobDescription = mutableStateOf("")
     var amount = mutableStateOf<Int>(0)

     fun clearForm(){
          firstName.value = ""
          email.value = ""
          jobDescription.value = ""
          amount.value = 0

     }
}

