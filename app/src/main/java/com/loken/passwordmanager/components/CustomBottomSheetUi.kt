package com.loken.passwordmanager.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.imeAnimationTarget
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.loken.passwordmanager.presentation.newAccount.AddAccountScreenContent
import com.loken.passwordmanager.ui.theme.backGroundColor


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomBottomSheetUi(
    modifier: Modifier = Modifier,
    onDismiss:()->Unit,
    content: @Composable () -> Unit,
    modalBottomSheetState: SheetState

) {

   val isKeyboardVisible =  WindowInsets.ime.getBottom(LocalDensity.current) > 0

    ModalBottomSheet(
        modifier = modifier
            .imePadding().navigationBarsPadding(),
        onDismissRequest = { onDismiss.invoke() },
        containerColor = backGroundColor,
        sheetState = modalBottomSheetState,
        dragHandle = { BottomSheetDefaults.DragHandle() },
    ) {
        Column(modifier = modifier.imePadding().navigationBarsPadding()) {
            content()
        }

    }
}

