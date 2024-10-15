package com.example.swiss.repo

import com.example.swiss.network.data.common.Activity
import com.example.swiss.network.data.common.UserModel
import com.example.swiss.network.utils.ResponseWrapper
import com.example.swiss.network.utils.safeHttpCall
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody

class UserRepo(private val client: HttpClient) {

    suspend fun getAllUsers() : ResponseWrapper<List<UserModel>> =
        safeHttpCall {
            client
                .get("users")
                .body()
        }

    suspend fun addActivity(
        userId : String?,
        activities : List<Activity>
    ) : ResponseWrapper<UserModel> =
        safeHttpCall {
            client
                .post("users/$userId/activities"){
                    setBody(activities)
                }
                .body()
        }
}