package com.loken.passwordmanager.data.repo

import com.loken.crypto.EncryptionUtil.decrypt
import com.loken.crypto.EncryptionUtil.encrypt
import com.loken.mappers.toPassword
import com.loken.mappers.toPasswordEntity
import com.loken.passwordmanager.data.dao.PasswordManagerDao
import com.loken.passwordmanager.model.Password
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class PasswordRepositoryImpl @Inject constructor(private val passwordManagerDao: PasswordManagerDao):PasswordRepository {

    override fun getAllPasswords(): Flow<List<Password>> {
        return flow {
            try {
                emit(passwordManagerDao.getAllPasswords().map {
                    it.toPassword()
                })
            }catch (e:Exception){
                e.printStackTrace()
            }
        }.flowOn(Dispatchers.IO).catch {
            it.printStackTrace()
        }

    }

    override fun getPasswordDetails(id: Int): Flow<Password?> {
        return flow {
            try {
                val passwordEntity = passwordManagerDao.getPasswordDetails(id).firstOrNull()
                val password = passwordEntity?.toPassword()?.copy(
                    password = decryptPassword(passwordEntity.password.toString())
                )
                emit(password)
            }catch (e:Exception){
                e.printStackTrace()
            }

        }.flowOn(Dispatchers.IO).catch { it.printStackTrace() }
    }

    override suspend fun insertPasswordItem(item: Password) {
        try {
            return passwordManagerDao.insertPassword(item.toPasswordEntity().copy(
                password =  encryptPassword(item.password.toString())
            ))
        }catch (e:Exception){
            e.printStackTrace()
        }
    }

    override suspend fun deletePasswordItem(id:Int) {
        try {
            return passwordManagerDao.deletePassword(id)
        }catch (e:Exception){
            e.printStackTrace()
        }
    }

    override suspend fun updatePasswordDetails(
        id: Int,
        accountName: String,
        email: String,
        password: String
    ) {
        try {
            return passwordManagerDao.updatePasswordDetailsById(
                id = id,
                accountName = accountName,
                email = email,
                password = encryptPassword(password)
            )
        }catch (e:Exception){
            e.printStackTrace()
        }
    }

    private suspend fun decryptPassword(encryptedPassword: String): String {
        return try {
            decrypt(encryptedPassword, "b0a7c6fbcdfb20b1512e8a4a6e5e7b5f")
        } catch (e: Exception) {
            e.printStackTrace()
            ""
        }
    }
    private suspend fun encryptPassword(passwordText:String):String?{
        return try {
            encrypt(passwordText,"b0a7c6fbcdfb20b1512e8a4a6e5e7b5f")
        }catch (e:Exception){
            e.printStackTrace()
            ""
        }
    }
}