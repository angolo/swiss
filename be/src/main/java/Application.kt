package base.main

import base.main.modules.data.User
import base.main.modules.routes.usersRoutes
import base.main.modules.utils.di.db.mongKoinModule
import com.mongodb.MongoException
import com.mongodb.kotlin.client.coroutine.MongoCollection
import com.mongodb.kotlin.client.coroutine.MongoDatabase
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import kotlinx.coroutines.*
import org.bson.BsonInt64
import org.bson.Document
import org.koin.core.context.startKoin
import org.koin.ktor.ext.inject


fun main(args: Array<String>) {
    startKoin {
        modules(
            mongKoinModule
        )
    }
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.startup() {
    install(ContentNegotiation) {
        json()
    }

    val db by inject<MongoDatabase>()
    CoroutineScope(Job() + Dispatchers.IO).launch {
        try {
            // Send a ping to confirm a successful connection
            val command = Document("ping", BsonInt64(1))
            db.runCommand(command)
            println("Connected to MongoDB!")
        } catch (me: MongoException) {
            println(me)
        }
    }


}

fun Application.initCollection() {
    val collection by inject<MongoCollection<User>>()
    runBlocking {
        if (collection.countDocuments() == 0L) {
            println("initializing user collection")
            collection.insertOne(
                User(
                    username = "Primo",
                )
            )
        }
    }
}


fun Application.routes() {
    usersRoutes()
}
