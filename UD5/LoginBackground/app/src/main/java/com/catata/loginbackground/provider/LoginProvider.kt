package com.catata.loginbackground.provider

import com.catata.loginbackground.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class LoginProvider {


    suspend fun doLogin(usr:String, passwd:String):User? = withContext(Dispatchers.IO){
        val users = userList.filter {
            it.username == usr && it.password == passwd
        }
        delay(2000)

        if(!users.isNullOrEmpty()) users.first() else null
    }



    companion object{
        val userList = listOf<User>(
            User("carlos", "12345", 25, "Carlos"),
            User("ana", "12345", 23, "Ana"),
            User("laura", "12345", 20, "Laura"),
            User("sara12", "12345", 30, "Sara"),
        )
    }

}