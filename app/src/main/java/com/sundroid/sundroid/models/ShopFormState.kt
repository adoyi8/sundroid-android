package com.sundroid.sundroid.models

import androidx.compose.runtime.mutableStateOf
import com.sundroid.sundroid.data.local.dao.database_models.Shop

class ShopFormState(){










     var name = mutableStateOf("")
     var address = mutableStateOf("")
     var owner = mutableStateOf("")
     var location =  mutableStateOf("")
     var phone =mutableStateOf("")
     val shopId = mutableStateOf(-1)


     fun clearForm(){
          name.value = ""
          address.value = ""
          owner.value = ""
          location.value = ""
          phone.value = ""
          shopId.value = -1


     }
     fun convertToFormState(shop: Shop){
          clearForm()
          shop.apply {
              this@ShopFormState.name.value = name
              this@ShopFormState.address.value = address
              this@ShopFormState.owner.value = owner
              this@ShopFormState.location.value = location
              this@ShopFormState.phone.value = phone
              this@ShopFormState.shopId.value = shopId

          }
     }

     fun getShopFromFormState(): Shop{
          var shop = Shop(
               name = name.value,
               address = address.value,
               owner = owner.value,
               location = location.value,
               shopId = shopId.value,


          )
          if(shopId.value>=0){
               shop.shopId = shopId.value
          }
          return shop;

     }
}

