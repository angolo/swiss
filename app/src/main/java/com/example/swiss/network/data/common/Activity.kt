package com.example.swiss.network.data.common

import kotlinx.serialization.Serializable


@Serializable
data class Activity(
    val _id: String? = null,
    val endDate: Long? = null,
    val name: String? = null,
    val type: String? = null
)