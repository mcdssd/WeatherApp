package com.example.weatherapp_marina.model

data class User(val name: String, val email: String)

class FBUser {
    var name : String ? = null
    var email : String? = null
    fun toUser() = User(name!!, email!!)
}
fun User.toFBUser() : FBUser {
    val fbUser = FBUser()
    fbUser.name = this.name
    fbUser.email = this.email
    return fbUser
}