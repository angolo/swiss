package com.example.swiss.ui.vm

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.swiss.network.data.common.Activity
import com.example.swiss.network.data.common.UserModel
import com.example.swiss.network.utils.ResponseWrapper
import com.example.swiss.repo.UserRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class ViewModel(
    private val userRepo: UserRepo
) : ViewModel() {

    var isLoading by mutableStateOf(false)

    private var _users = mutableStateListOf<UserModel>()
    val users
        get() = _users

    fun getAllUsers() {
        viewModelScope.launch(Dispatchers.IO) {
            isLoading = true
            users.clear()
            userRepo.getAllUsers().run {
                when (this) {
                    is ResponseWrapper.Success -> _users.addAll(data)
                    is ResponseWrapper.Error -> _users.clear()
                }
            }
            isLoading = false
        }
    }

    fun addActivity(
        userId: String?,
        activity: Activity
    ) = viewModelScope.async(Dispatchers.IO) {
        isLoading = true
        kotlinx.coroutines.delay(800)
        val result = userRepo.addActivity(
            userId,
            listOf(activity)
        )
        isLoading = false
        return@async result
    }


}