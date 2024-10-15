package base.main.modules.routes

import base.main.modules.data.Activity
import base.main.modules.data.User
import com.mongodb.client.model.Filters
import com.mongodb.client.model.Filters.eq
import com.mongodb.client.model.FindOneAndUpdateOptions
import com.mongodb.client.model.ReturnDocument
import com.mongodb.client.model.Updates
import com.mongodb.kotlin.client.coroutine.MongoCollection
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.toList
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.encodeToJsonElement
import org.bson.types.ObjectId
import org.koin.ktor.ext.inject
import java.util.concurrent.TimeUnit


//todo: check
fun Application.usersRoutes() {

    val usersCollection by inject<MongoCollection<User>>()

    routing {
        get("/users") {
            usersCollection.find().toList().let { users ->
                call.respond(
                    Json.encodeToJsonElement(users)
                )
            }
        }

        get("/users/{id}") {
            call.parameters["id"]?.let {
                val user = usersCollection
                    .withDocumentClass<User>()
                    .find(
                        eq("_id", ObjectId(it))
                    ).firstOrNull()
                call.respondNullable(user ?: HttpStatusCode.NotFound)
            } ?: call.respond(HttpStatusCode.BadRequest)
        }

        post("/users") {
            val user = call.receive<User>()
            usersCollection.insertOne(user)
            call.respond(HttpStatusCode.Created)
        }

        delete("/users/{id}") {
            call.parameters["id"]?.let { //todo: sto let non serve
                usersCollection
                    .withDocumentClass<User>()
                    .deleteOne(
                        eq("_id", ObjectId(it))
                    )//todo: controllare vada in success
                call.respondNullable(HttpStatusCode.Accepted)
            } ?: call.respond(HttpStatusCode.BadRequest)
        }

        //attività
        post("/users/{id}/activities") {
            val userId = call.parameters["id"]
            val activities = call.receive<List<Activity>>()

            val result = usersCollection.findOneAndUpdate(
                eq("_id",ObjectId(userId)),
                Updates.pushEach(User::activities.name,activities),
                FindOneAndUpdateOptions().returnDocument(ReturnDocument.AFTER)
            )
            call.respondNullable(HttpStatusCode.Created,result)
        }

    }
}