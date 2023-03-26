package com.sundroid.sundroid.models

import androidx.compose.runtime.mutableStateOf
import com.sundroid.sundroid.data.local.dao.database_models.Staff

class StaffFormState(){










     var name = mutableStateOf("")
     var email = mutableStateOf("")
     var phone =mutableStateOf("")
     val staffId = mutableStateOf(-1)


     fun clearForm(){
          name.value = ""

          phone.value = ""
          phone.value = ""
          staffId.value = -1


     }
     fun convertToFormState(staff: Staff){
          clearForm()
          staff.apply {
              this@StaffFormState.name.value = name
              this@StaffFormState.phone.value = phone
              this@StaffFormState.staffId.value = staffId
               this@StaffFormState.email.value = email

          }
     }

     fun getShopFromFormState(): Staff{
          var staff = Staff(
               name = name.value,
               email = email.value,
               phone = phone.value,


          )
          if(staffId.value>=0){
               staff.staffId = staffId.value
          }
          return staff;

     }
}

