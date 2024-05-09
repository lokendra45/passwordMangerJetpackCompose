package com.loken.passwordmanager.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.loken.passwordmanager.ui.theme.backGroundColor

@Composable
fun CommonTextField(
    modifier: Modifier = Modifier,
    textFieldValue:String,
    onTextValueChange:(String)->Unit,
    placeHolderText:String,
    supportingText:String?=null
) {
    OutlinedTextField(
        modifier = modifier.fillMaxWidth(),
        value = textFieldValue,
        onValueChange = {
            onTextValueChange.invoke(it)
        },
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedContainerColor = Color.White,
            unfocusedBorderColor = Color.Gray.copy(alpha = 0.4f)
        ),
        singleLine = true,
        placeholder = {
            Text(text = placeHolderText, color = Color.Gray.copy(alpha = 0.5f))
        },
        supportingText = { Text(text = supportingText.orEmpty(), color = Color.Red.copy(alpha = 0.6f))}
        )


}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun CommonTextFieldPreview(modifier: Modifier = Modifier) {
    Column(modifier = Modifier
        .padding(20.dp)
        .background(color = backGroundColor)) {
        CommonTextField(textFieldValue = "", onTextValueChange = {}, placeHolderText = "Account Name")
    }

}