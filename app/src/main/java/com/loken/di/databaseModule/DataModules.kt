package com.loken.di.databaseModule
import android.content.Context
import androidx.room.Room
import com.loken.passwordmanager.data.dao.PasswordManagerDao
import com.loken.passwordmanager.data.database.PasswordManagerDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
  @Singleton
  @Provides
  fun provideDataBase(@ApplicationContext context: Context): PasswordManagerDb {
    return Room.databaseBuilder(
      context.applicationContext,
      PasswordManagerDb::class.java,
      "password.db"
    ).build()
  }

  @Provides
  fun provideAttendInDao(database: PasswordManagerDb): PasswordManagerDao = database.passwordDao()

}
