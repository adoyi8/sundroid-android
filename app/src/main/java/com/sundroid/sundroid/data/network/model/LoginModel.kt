package com.sundroid.sundroid.data.network.model

import com.sundroid.sundroid.data.local.dao.database_models.RoomUserEntity

data class LoginModel(
    var email: String?,
    var firstName: String?, var lastName: String?, var photoUrl: String?, var fullName:String?){














    fun getHashMap(): HashMap<String, String>{
        var map = HashMap<String, String>();
        email?.let {
            map.put("email", email!!);
        }
        firstName?.let {
            map.put("firstName", firstName!!);
        }
        lastName.let {
            map.put("lastName", lastName!!);
        }
        return map;


    }
    fun getRoomUserEntity(): RoomUserEntity{


        return RoomUserEntity(email = email!!, displayName = fullName, familyName = lastName, givenName = firstName,accessToken= "",photoUrl = photoUrl.toString(),serverAuthCode= "");


    }
}
