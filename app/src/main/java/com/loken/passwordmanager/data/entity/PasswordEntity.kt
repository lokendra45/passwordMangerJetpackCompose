package com.loken.passwordmanager.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "passwords")
data class PasswordEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    val accountName: String?,
    val email: String?,
    val password:String?
)

