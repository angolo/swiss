package com.example.swiss.network.data.common

import kotlinx.serialization.Serializable

@Serializable
data class UserModel(
    val _id: String? = null,
    val activities: List<Activity>? = null,
    val username: String
)