package com.example.swiss.ui.activities.sheets

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.swiss.network.data.common.Activity
import com.example.swiss.network.data.common.UserModel
import com.example.swiss.network.utils.ResponseWrapper
import com.example.swiss.ui.theme.SwissTheme
import com.example.swiss.ui.vm.ViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun AddActivityBottomSheet(
    bottomSheetState: SheetState = rememberModalBottomSheetState(),
    showBottomSheet: MutableState<Boolean> = mutableStateOf(false),
    selectedUserId: String? = null,
    viewModel: ViewModel? = null
) {
    val text = remember { mutableStateOf<String?>(null) }
    val coroutineScope = rememberCoroutineScope()
    val result = remember { mutableStateOf<ResponseWrapper<UserModel>?>(null) }

    val keyboard = LocalSoftwareKeyboardController.current

    ModalBottomSheet(
        onDismissRequest = {
            keyboard?.hide()
            showBottomSheet.value = false
            result.value = null
            text.value = null
        },
        sheetState = bottomSheetState
    ) {
        Box {
            Column {

                TextField(
                    value = text.value ?: "",
                    onValueChange = {
                        text.value = it
                    },
                    label = { Text("Inserisci il nome dell'attività") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                )

                Button(
                    enabled = text.value.isNullOrEmpty().not(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp),
                    onClick = {
                        coroutineScope.launch {
                            result.value = viewModel?.addActivity(
                                selectedUserId,
                                Activity(name = text.value)
                            )?.await()
                        }
                    }
                ) {
                    if (viewModel?.isLoading == true)
                        CircularProgressIndicator(
                            color = Color.White
                        )
                    else
                        Text("Conferma")
                }
            }

            //on result
            if (result.value != null) {
                result.value!!.onSuccess { data ->
                    viewModel?.users?.indexOfFirst { it._id == selectedUserId }?.let {
                        if (it > -1)
                            viewModel.users[it] = data
                    }
                }
                LaunchedEffect(Unit) {
                    keyboard?.hide()
                    showBottomSheet.value = false
                    result.value = null
                    text.value = null
                    bottomSheetState.hide()
                }
            }
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
private fun AddActivitySheetPreview() {
    SwissTheme {
        val sheetState = rememberModalBottomSheetState()
        LaunchedEffect(Unit) { sheetState.show() }

        AddActivityBottomSheet(
            sheetState
        )
    }
}