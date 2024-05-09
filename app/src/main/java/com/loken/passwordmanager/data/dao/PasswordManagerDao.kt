package com.loken.passwordmanager.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import com.loken.passwordmanager.data.entity.PasswordEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PasswordManagerDao {
    @Insert
    suspend fun insertPassword(user: PasswordEntity)

    @Query("SELECT * FROM passwords WHERE id=:id")
    fun getPasswordDetails(id: Int): Flow<PasswordEntity?>

    @Query("DELETE FROM passwords WHERE id =:id ",)
    suspend fun deletePassword(id:Int)

    @Query("SELECT * FROM passwords")
    suspend fun getAllPasswords(): List<PasswordEntity>

    @Query("UPDATE passwords SET accountName = :accountName, email = :email, password = :password WHERE id = :id")
    suspend fun updatePasswordDetailsById(id: Int, accountName: String?, email: String?, password: String?)
}
