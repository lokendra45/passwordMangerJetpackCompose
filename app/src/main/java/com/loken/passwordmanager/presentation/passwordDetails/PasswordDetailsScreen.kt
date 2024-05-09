package com.loken.passwordmanager.presentation.passwordDetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.loken.passwordmanager.components.AccountDetail
import com.loken.passwordmanager.components.AccountDetailItem
import com.loken.passwordmanager.components.CustomButton
import com.loken.passwordmanager.model.Password
import com.loken.passwordmanager.ui.theme.deleteButtonColor
import com.loken.passwordmanager.ui.theme.fabButtonColor


@Composable
fun PasswordDetailsScreen(
    modifier: Modifier = Modifier,
    password: Password,
    onEditButtonClick:(Password)->Unit,
    onPasswordDeleteClick:(Int?)->Unit
) {
    Column(modifier = Modifier
        .padding(20.dp)
        .fillMaxHeight()
        .fillMaxWidth(),
    ) {
        Text(text = "Account Details", fontSize = 20.sp, color = fabButtonColor, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(30.dp))
        val accountType = AccountDetail(label = "Account Type", value = password.accountName.orEmpty())
        val usernameEmail = AccountDetail(label = "Username/ Email", value = password.email.orEmpty())
        val passwordValue = AccountDetail(label = "Password", value = password.password.orEmpty())

        Column(verticalArrangement = Arrangement.spacedBy(20.dp), modifier = Modifier
            .fillMaxWidth()) {
            AccountDetailItem(accountDetail = accountType)
            AccountDetailItem(accountDetail = usernameEmail)
            AccountDetailItem(accountDetail = passwordValue)

        }
        Spacer(modifier = Modifier.height(50.dp))
        Row( modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            CustomButton(buttonText = "Edit", onButtonClick = {
                onEditButtonClick.invoke(password)

            }, modifier = Modifier.width(154.dp))
            CustomButton(buttonText = "Delete", onButtonClick = {
                             onPasswordDeleteClick.invoke(password.id)

            }, modifier = Modifier.width(154.dp), containerColor = deleteButtonColor
            )
        }



    }


}



