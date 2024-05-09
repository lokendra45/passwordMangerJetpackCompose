package com.loken.passwordmanager.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.loken.passwordmanager.components.CustomPasswordBox
import com.loken.passwordmanager.model.Password
import com.loken.passwordmanager.ui.theme.PasswordManagerTheme

@Composable
fun HomeScreenContent(
    modifier: Modifier = Modifier,
    password: List<Password>,
    onItemClick:(Int)->Unit
) {
    Column(modifier = modifier.padding(10.dp)) {
        LazyColumn(contentPadding = PaddingValues(10.dp, vertical = 10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)) {
            items(password){
                CustomPasswordBox(password = it, onItemClick = {itemId->
                    onItemClick.invoke(itemId)
                })
            }
        }


    }


}

