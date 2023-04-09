package ru.rifnur.tasktracker

import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.cio.*
import io.ktor.server.engine.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.json.Json
import ru.rifnur.tasktracker.app.v2.v2Task

fun main() {
    embeddedServer(CIO, port = 8080) {

        install(Routing)
        install(ContentNegotiation) {
            json(Json {
                isLenient = true
                prettyPrint = true
                ignoreUnknownKeys = true
            })
        }
        module()
    }.start(wait = true)
}

    fun Application.module() {
        routing {
            get("/") {
                call.respondText("Hello, Rifnur!")
            }
            route("v2") {
                v2Task()
            }
        }
    }

