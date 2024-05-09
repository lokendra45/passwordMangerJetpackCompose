package com.loken.passwordmanager.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.loken.passwordmanager.data.dao.PasswordManagerDao
import com.loken.passwordmanager.data.entity.PasswordEntity

@Database(entities = [PasswordEntity::class], version = 1, exportSchema = false)
abstract class PasswordManagerDb:RoomDatabase() {
    abstract fun passwordDao():PasswordManagerDao
}
