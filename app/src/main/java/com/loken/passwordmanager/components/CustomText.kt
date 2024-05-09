package com.loken.passwordmanager.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.loken.passwordmanager.R

data class AccountDetail(
    val label: String,
    val value: String
)

@Composable
fun AccountDetailItem(
    modifier: Modifier = Modifier,
    accountDetail: AccountDetail,
) {
    var passwordVisible by remember { mutableStateOf(false) }
    val displayText = if (accountDetail.label == "Password") {
        if (passwordVisible) accountDetail.value else "********"
    } else {
        accountDetail.value
    }
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(9.dp)) {
        Text(text = accountDetail.label, color = Color.Gray.copy(alpha = 0.5f), fontSize = 14.sp)
        Row {
            Text(text = displayText, fontSize = 17.sp, fontWeight = FontWeight.Bold)
            if (accountDetail.label.equals("Password")){
                Spacer(modifier = Modifier.weight(1f))
                Image(painter = painterResource(id = R.drawable.eye_1), contentDescription = "",
                    modifier = modifier.clickable {
                        passwordVisible = !passwordVisible

                })
            }

        }

    }
}
