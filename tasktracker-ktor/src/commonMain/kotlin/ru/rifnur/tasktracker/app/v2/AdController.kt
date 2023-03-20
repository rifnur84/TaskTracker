package ru.rifnur.tasktracker.app.v2

import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import ru.rifnur.tasktracker.api.v2.apiV2Mapper
import ru.rifnur.tasktracker.api.v2.models.*
import ru.rifnur.tasktracker.common.ComTaskContext
import ru.rifnur.tasktracker.common.models.ComTask
import ru.rifnur.tasktracker.mappers.v2.*
import ru.rifnur.tasktracker.stubs.ComTaskStub

suspend fun ApplicationCall.createTask() {
    val request = apiV2Mapper.decodeFromString<TaskCreateRequest>(receiveText())
    val context = ComTaskContext()
    context.fromTransport(request)
    context.taskResponse = ComTaskStub.get()
    respond(apiV2Mapper.encodeToString(context.toTransportCreate()))
}

suspend fun ApplicationCall.readTask() {
    val request = apiV2Mapper.decodeFromString<TaskReadRequest>(receiveText())
    val context = ComTaskContext()
    context.fromTransport(request)
    context.taskResponse = ComTaskStub.get()
    respond(apiV2Mapper.encodeToString(context.toTransportRead()))
}

suspend fun ApplicationCall.updateTask() {
    val request = apiV2Mapper.decodeFromString<TaskUpdateRequest>(receiveText())
    val context = ComTaskContext()
    context.fromTransport(request)
    context.taskResponse = ComTaskStub.get()
    respond(apiV2Mapper.encodeToString(context.toTransportUpdate()))
}

suspend fun ApplicationCall.deleteTask() {
    val request = apiV2Mapper.decodeFromString<TaskDeleteRequest>(receiveText())
    val context = ComTaskContext()
    context.fromTransport(request)
    context.taskResponse = ComTaskStub.get()
    respond(apiV2Mapper.encodeToString(context.toTransportDelete()))
}

suspend fun ApplicationCall.searchTask() {
    val request = apiV2Mapper.decodeFromString<TaskSearchRequest>(receiveText())
    val context = ComTaskContext()
    context.fromTransport(request)
    context.tasksResponse.addAll(ComTaskStub.prepareSearchList("Заявка"))
    respond(apiV2Mapper.encodeToString(context.toTransportSearch()))
}
