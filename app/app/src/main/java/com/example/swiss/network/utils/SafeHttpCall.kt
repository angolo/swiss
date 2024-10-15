package com.example.swiss.network.utils

import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException


inline fun <T> safeHttpCall(call: () -> T) =
    try {
        ResponseWrapper.Success(call.invoke())
    } catch (e: RedirectResponseException) {
        ResponseWrapper.Error(e)
    } catch (e: ClientRequestException) {
        ResponseWrapper.Error(e)
    } catch (e: ServerResponseException) {
        ResponseWrapper.Error(e)
    } catch (e: Exception) {
        ResponseWrapper.Error(e)
    }

