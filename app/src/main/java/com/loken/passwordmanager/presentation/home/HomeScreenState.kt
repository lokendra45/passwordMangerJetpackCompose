package com.loken.passwordmanager.presentation.home

import com.loken.passwordmanager.model.Password

data class HomeScreenState(
    val id:Int?=null,
    val accountName:String?="",
    val email:String?="",
    val password:String?="",
    val supportTextMessageEmail:String?=null,
    val supportTextMessagePassword:String?=null,
    val supportTextMessageAccount:String?=null,
    val passwordData:List<Password> = emptyList(),
    val passwordDetails:Password?=null,
    val isAddUpdateSuccess:Boolean=false
){

}