package com.example.swiss.network.data.common

import kotlinx.serialization.Serializable

@Serializable
data class Activity(
    val _id: String? = null,
    val endDate: String? = null,
    val name: String? = null,
    val startDate: String? = null,
    val type: String? = null
)