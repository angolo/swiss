package base.main.modules.utils.logs

import io.ktor.server.application.*
import io.ktor.server.engine.*


internal fun Application.logs(){
    (environment as ApplicationEngineEnvironment).connectors.forEach { connector->
        println("server listening on ${connector.port}")
    }
}