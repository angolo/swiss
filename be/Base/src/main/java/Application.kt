package base.main

import io.ktor.server.routing.*
import io.ktor.server.application.*
import io.ktor.server.response.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.module() {
    routing {
        get("/home") {
            call.respondText("hll wrld")
        }
    }
}