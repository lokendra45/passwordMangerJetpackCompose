package com.loken.di.databaseModule


import com.loken.passwordmanager.data.repo.PasswordRepository
import com.loken.passwordmanager.data.repo.PasswordRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
  @Binds
  abstract fun bindLoginRepository(
    passwordRepositoryImpl: PasswordRepositoryImpl
  ): PasswordRepository
}
