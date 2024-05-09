package com.loken.passwordmanager

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.loken.passwordmanager.components.CustomBottomSheetUi
import com.loken.passwordmanager.presentation.home.HomeScreenContent
import com.loken.passwordmanager.presentation.home.HomeViewModel
import com.loken.passwordmanager.presentation.newAccount.AddAccountScreenContent
import com.loken.passwordmanager.presentation.passwordDetails.PasswordDetailsScreen
import com.loken.passwordmanager.ui.theme.PasswordManagerTheme
import com.loken.passwordmanager.ui.theme.fabButtonColor
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : FragmentActivity() {
    private val homeViewModel by viewModels<HomeViewModel>()
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            PasswordManagerTheme {
                val snackBarHostState = remember { SnackbarHostState() }
                var addPasswordSheet by remember { mutableStateOf(false) }
                var showPasswordDetailSheet by remember { mutableStateOf(false) }
                val scope = rememberCoroutineScope()
                val modalBottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
                val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
                val homeState by homeViewModel.homeState.collectAsStateWithLifecycle()
                LaunchedEffect(key1 = modalBottomSheetState.currentValue) {
                    if (!modalBottomSheetState.isVisible){
                        homeViewModel.resetState()
                    }

                }
                LaunchedEffect(key1 = homeViewModel,homeState.isAddUpdateSuccess) {
                    if (homeState.isAddUpdateSuccess){
                        addPasswordSheet =false
                        modalBottomSheetState.hide()
                    }

                }
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    snackbarHost = { SnackbarHost(hostState = snackBarHostState)},
                    floatingActionButtonPosition = FabPosition.End,
                    floatingActionButton = {
                        FloatingActionButton(
                            shape = RoundedCornerShape(5.dp),
                            onClick = { addPasswordSheet=true },
                            modifier = Modifier.size(50.dp),
                            containerColor = fabButtonColor) {
                            Image(
                                painter = painterResource(id = R.drawable.union),
                                contentDescription ="" ,Modifier.size(20.dp))
                        }
                    },
                    topBar = { TopAppBar(title = {
                        Text(text = "Password Manager")
                    })}
                ) { innerPadding ->
                    if (addPasswordSheet){
                        Log.d("", "onCreate: $homeState")
                        CustomBottomSheetUi(modalBottomSheetState = modalBottomSheetState, content = {
                            AddAccountScreenContent(
                                accountNameText =homeState.accountName.orEmpty(),
                                id = homeState.id,
                                onAccountNameChange = {homeViewModel.onAccountNameChange(it)},
                                emailText = homeState.email.orEmpty(),
                                onEmailChange = {homeViewModel.onEmailValueChange(it)},
                                passwordText = homeState.password.orEmpty(),
                                onPasswordValueChange = {homeViewModel.onPasswordValueChange(it)},
                                onAddAccountClick = {
                                    homeViewModel.savePassword(homeState.id)
                                },
                                supportingTextEmail = homeState.supportTextMessageEmail,
                                supportingAccount = homeState.supportTextMessageAccount,
                                supportingTextPassword = homeState.supportTextMessagePassword
                            )
                        }, onDismiss = {
                            addPasswordSheet=false
                            scope.launch {
                                modalBottomSheetState.hide()
                            }
                        })
                    }
                    if (showPasswordDetailSheet){
                        CustomBottomSheetUi(modalBottomSheetState = sheetState,onDismiss = { showPasswordDetailSheet=false }, content = {
                            homeState.passwordDetails?.let { PasswordDetailsScreen(password = it,
                                onPasswordDeleteClick = {passwordId->
                                    homeViewModel.deletePasswordItem(passwordId).also {
                                        showPasswordDetailSheet =false
                                    }
                                },
                                onEditButtonClick = {updatePasswordData->
                                    homeViewModel.setEditPasswordData(password = updatePasswordData)
                                    addPasswordSheet = true
                                    showPasswordDetailSheet=false
                                }) }

                        })
                    }
                    Column(modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize()) {
                        HorizontalDivider(color = Color.Gray.copy(alpha = 0.5f),)
                        HomeScreenContent(password = homeState.passwordData, onItemClick = {
                            showPasswordDetailSheet = true
                            homeViewModel.fetchPasswordDetails(it)
                        })

                    }

                }
            }
        }
    }
}


