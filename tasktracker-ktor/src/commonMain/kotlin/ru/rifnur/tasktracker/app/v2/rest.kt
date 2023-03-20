package ru.rifnur.tasktracker.app.v2

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Route.v2Task() {
    route("task") {
        post("create") {
            call.createTask()
        }
        post("read") {
            call.readTask()
        }
        post("update") {
            call.updateTask()
        }
        post("delete") {
            call.deleteTask()
        }
        post("search") {
            call.searchTask()
        }
    }
}

