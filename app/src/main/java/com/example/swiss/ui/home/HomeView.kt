package com.example.swiss.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.swiss.ui.items.UserItem
import com.example.swiss.ui.vm.ViewModel


@Composable
fun HomeView(
    nav: NavController,
    vm: ViewModel
) {
    LaunchedEffect(Unit) {
        vm.getAllUsers()
    }
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            items(vm.users) {
                UserItem(it) {
                    nav.navigate("Activities/${it._id}")
                }
            }
        }

        if (vm.isLoading)
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        else if (vm.users.isEmpty())
            if (vm.users.isEmpty())
                Text(
                    "Nessun utente trovato!",
                    Modifier.align(Alignment.Center)
                )
    }


}