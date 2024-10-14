package com.example.swiss.ui.activities

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.swiss.ui.activities.sheets.AddActivityBottomSheet
import com.example.swiss.ui.vm.ViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActivitiesView(
    selectedUserId: String?,
    viewModel: ViewModel
) {

    val activities = viewModel.users.find { it._id == selectedUserId }?.activities

    val showBottomSheet = remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState()
    val coroutineScope = rememberCoroutineScope()

    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        if (activities?.isEmpty()?.not() == true) {
            Column {

                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    items(activities) {
                        Text("${it.name}")
                    }
                }
            }
        } else
            Text(
                "Nessuna attività trovata!",
                modifier = Modifier.align(Alignment.Center)
            )


        SmallFloatingActionButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(10.dp),
            onClick = {
                coroutineScope.launch {
                    showBottomSheet.value = true
                    sheetState.show()
                }
            }
        ) {
            Icon(Icons.Rounded.Add, contentDescription = null)
        }
    }

    if (showBottomSheet.value)
        AddActivityBottomSheet(
            sheetState,
            showBottomSheet,
            selectedUserId,
            viewModel
        )

}
