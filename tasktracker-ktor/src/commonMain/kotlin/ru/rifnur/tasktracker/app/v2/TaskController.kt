package ru.rifnur.tasktracker.app.v2

import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import ru.rifnur.tasktracker.api.v2.apiV2Mapper
import ru.rifnur.tasktracker.api.v2.models.*
import ru.rifnur.tasktracker.app.ComTaskAppSettings
import ru.rifnur.tasktracker.app.process
import ru.rifnur.tasktracker.common.ComTaskContext
import ru.rifnur.tasktracker.common.models.ComTaskCommand
import ru.rifnur.tasktracker.logging.common.IMpLogWrapper
import ru.rifnur.tasktracker.mappers.v2.*
import ru.rifnur.tasktracker.stubs.ComTaskStub

suspend fun ApplicationCall.createTask(appSettings: ComTaskAppSettings, logger: IMpLogWrapper)  =
    processV2<TaskCreateRequest, TaskCreateResponse>(appSettings, logger, "task-create", ComTaskCommand.CREATE)


suspend fun ApplicationCall.readTask(appSettings: ComTaskAppSettings, logger: IMpLogWrapper) =
    processV2<TaskReadRequest, TaskReadResponse>(appSettings, logger, "task-read", ComTaskCommand.READ)

suspend fun ApplicationCall.updateTask(appSettings: ComTaskAppSettings, logger: IMpLogWrapper) =
    processV2<TaskUpdateRequest, TaskUpdateResponse>(appSettings, logger, "task-update", ComTaskCommand.UPDATE)

suspend fun ApplicationCall.deleteTask(appSettings: ComTaskAppSettings, logger: IMpLogWrapper)  =
    processV2<TaskDeleteRequest, TaskDeleteResponse>(appSettings, logger, "task-delete", ComTaskCommand.DELETE)

suspend fun ApplicationCall.searchTask(appSettings: ComTaskAppSettings, logger: IMpLogWrapper)  =
    processV2<TaskSearchRequest, TaskSearchResponse>(appSettings, logger, "task-search", ComTaskCommand.SEARCH)
