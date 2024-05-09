package com.loken.passwordmanager.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.loken.passwordmanager.R
import com.loken.passwordmanager.model.Password

@Composable
fun CustomPasswordBox(
    modifier: Modifier = Modifier,
    password: Password,
    onItemClick:(Int)->Unit
) {
    Card(modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(2.dp),
        shape = RoundedCornerShape(50.dp)
    ) {
            Row(
                modifier = Modifier.padding(20.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(text = password.accountName.orEmpty(),
                    fontSize = 20.sp,
                    fontFamily = FontFamily.SansSerif,)
                Spacer(modifier = modifier.padding(start = 10.dp))
                Text(
                    text = "*******",
                    fontSize = 18.sp,
                    letterSpacing = 1.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.Gray.copy(alpha = 0.5f)
                )
                Spacer(modifier = modifier.weight(1f))
                Image(painter =
                painterResource(id = R.drawable.vector_5),
                    contentDescription = "", modifier = Modifier.clickable {
                        password.id?.let { onItemClick.invoke(it) }
                    })
            }

        }
}


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun CustomPasswordBoxPreview(modifier: Modifier = Modifier) {
    Column(modifier = Modifier.padding(20.dp)) {
        CustomPasswordBox(password = Password(
            id = 0,
            accountName = "",
            email = "",
            password = "",
        ), onItemClick = {})
    }

}