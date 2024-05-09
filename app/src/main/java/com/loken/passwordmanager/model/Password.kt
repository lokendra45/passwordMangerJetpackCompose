package com.loken.passwordmanager.model


data class Password(
    val id: Int?=null,
    val accountName: String?,
    val email: String?,
    val password:String?
)