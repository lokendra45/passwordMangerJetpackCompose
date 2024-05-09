package com.loken.passwordmanager.presentation.newAccount

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.loken.passwordmanager.common.CommonTextField
import com.loken.passwordmanager.components.CustomButton


@Composable
fun AddAccountScreenContent(
    modifier: Modifier = Modifier,
    id:Int?,
    accountNameText:String,
    onAccountNameChange:(String)->Unit,
    emailText:String,
    onEmailChange:(String)->Unit,
    passwordText:String,
    onPasswordValueChange:(String)->Unit,
    onAddAccountClick:()->Unit,
    supportingTextEmail:String?,
    supportingAccount:String?,
    supportingTextPassword:String?,
) {
    Column(modifier = modifier.padding(18.dp).imePadding().navigationBarsPadding(), verticalArrangement = Arrangement.spacedBy(10.dp)) {
        CommonTextField(textFieldValue =accountNameText
            , onTextValueChange ={onAccountNameChange.invoke(it)} ,
            placeHolderText = "Account Name",
            supportingText = supportingAccount
        )
        CommonTextField(textFieldValue =emailText
            , onTextValueChange ={onEmailChange.invoke(it)} ,
            placeHolderText = "Username/ Email",
            supportingText = supportingTextEmail
        )
        CommonTextField(textFieldValue =passwordText
            , onTextValueChange ={onPasswordValueChange.invoke(it)} ,
            placeHolderText = "Password",
            supportingText = supportingTextPassword
        )
        Spacer(modifier = Modifier.height(10.dp))
        CustomButton(buttonText = if (id==null) "Add New Account" else "Update", onButtonClick = {
            onAddAccountClick.invoke()
        })

    }
    
    
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun AddAccountPreview() {
    AddAccountScreenContent(
        id =0 ,
        accountNameText = "",
        onAccountNameChange = {},
        emailText = "",
        onEmailChange = {},
        passwordText = "",
        onPasswordValueChange = {},
        onAddAccountClick = {},
        supportingTextEmail = null,
        supportingAccount = null,
        supportingTextPassword = null,

    )
    
}