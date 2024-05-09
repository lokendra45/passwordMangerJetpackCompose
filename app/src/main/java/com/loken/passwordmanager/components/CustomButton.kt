package com.loken.passwordmanager.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.loken.passwordmanager.ui.theme.buttonBackgroundColor

@Composable
fun CustomButton(modifier: Modifier = Modifier,buttonText:String,onButtonClick:()->Unit,containerColor: Color?=null) {
    ElevatedButton(
        modifier = modifier.fillMaxWidth().height(44.dp),
        colors = ButtonDefaults.elevatedButtonColors(
            containerColor = containerColor?: buttonBackgroundColor,
            contentColor = Color.White
        ),
        onClick = { onButtonClick.invoke() }
    ) {
        Text(text = buttonText,
            fontFamily = FontFamily.SansSerif,
            fontSize = 17.sp, fontWeight = FontWeight.Bold)
        
    }

    
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun CustomButtonPreview(modifier: Modifier = Modifier) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()
        .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        CustomButton(buttonText = "Add New Account", onButtonClick = {})
    }


}