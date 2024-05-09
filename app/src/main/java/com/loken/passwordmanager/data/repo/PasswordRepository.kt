package com.loken.passwordmanager.data.repo

import com.loken.passwordmanager.model.Password
import kotlinx.coroutines.flow.Flow

interface PasswordRepository {
    fun getAllPasswords(): Flow<List<Password>>
    fun getPasswordDetails(id: Int): Flow<Password?>
    suspend fun insertPasswordItem(item: Password)
    suspend fun deletePasswordItem(id:Int)
    suspend fun updatePasswordDetails(id: Int,accountName:String,email:String,password: String)
}