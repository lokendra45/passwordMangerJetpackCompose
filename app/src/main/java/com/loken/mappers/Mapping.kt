package com.loken.mappers


import com.loken.passwordmanager.data.entity.PasswordEntity
import com.loken.passwordmanager.model.Password

fun PasswordEntity.toPassword() = Password(
  id = id,
  accountName = accountName,
  email = email,
  password = password
)

fun Password.toPasswordEntity()=PasswordEntity(
  id = id,
  accountName = accountName,
  email = email,
  password = password
)
