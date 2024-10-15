package base.main.modules.utils.di.db

import base.main.modules.data.User
import com.mongodb.kotlin.client.coroutine.MongoClient
import com.mongodb.kotlin.client.coroutine.MongoCollection
import com.mongodb.kotlin.client.coroutine.MongoDatabase
import com.typesafe.config.ConfigFactory
import org.koin.core.annotation.Singleton
import org.koin.dsl.module

val mongKoinModule = module {
    single<MongoClient> { getMongoConnection() }
    single<MongoDatabase> { getSwissDb(get()) }
    single<MongoCollection<User>> { getUsersCollection(get()) }
}

internal fun getMongoConnection() =
    MongoClient.create(
        ConfigFactory.load().getString("ktor.deployment.mongoDb")
    )

@Singleton
internal fun getSwissDb(mongoClient: MongoClient) = mongoClient.getDatabase("Swiss")

@Singleton
internal fun getUsersCollection(db : MongoDatabase) = db.getCollection<User>("users")
