package base.main.modules.utils

import base.main.modules.utils.logs.logs
import com.mongodb.kotlin.client.coroutine.MongoClient
import io.ktor.server.application.*
import org.koin.core.context.startKoin
import org.koin.dsl.module

fun Application.utils(){
    logs()
}

