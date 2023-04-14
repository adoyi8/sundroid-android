package com.sundroid.sundroid.data.network.model

data class LoginModel(
    var email: String?,
    var firstName: String?, var lastName: String?){














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
}
