package ru.rifnur.tasktracker.app.v2

import io.ktor.server.application.*
import io.ktor.server.routing.*
import ru.rifnur.tasktracker.app.ComTaskAppSettings

fun Route.v2Task(appSettings: ComTaskAppSettings) {
    val loggerTask = appSettings.corSettings.loggerProvider.logger(Route::v2Task)
    route("task") {
        post("create") {
            call.createTask(appSettings, loggerTask)
        }
        post("read") {
            call.readTask(appSettings, loggerTask)
        }
        post("update") {
            call.updateTask(appSettings, loggerTask)
        }
        post("delete") {
            call.deleteTask(appSettings, loggerTask)
        }
        post("search") {
            call.searchTask(appSettings, loggerTask)
        }
    }
}

