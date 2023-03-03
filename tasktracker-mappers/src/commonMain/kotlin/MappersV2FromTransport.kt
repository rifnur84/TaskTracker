package ru.rifnur.tasktracker.mappers.v2

import ru.rifnur.tasktracker.api.v2.models.*
import ru.rifnur.tasktracker.common.ComTaskContext
import ru.rifnur.tasktracker.common.models.*
import ru.rifnur.tasktracker.common.models.ComTaskWorkMode
import ru.rifnur.tasktracker.common.stubs.ComTaskStubs
import ru.rifnur.tasktracker.mappers.v2.exceptions.UnknownRequestClass

fun ComTaskContext.fromTransport(request: IRequest) = when (request) {
    is TaskCreateRequest -> fromTransport(request)
    is TaskReadRequest -> fromTransport(request)
    is TaskUpdateRequest -> fromTransport(request)
    is TaskDeleteRequest -> fromTransport(request)
    is TaskSearchRequest -> fromTransport(request)
    else -> throw UnknownRequestClass(request::class)
}

private fun String?.toTaskId() = this?.let { ComTaskId(it) } ?: ComTaskId.NONE
private fun String?.toTaskWithId() = ComTask(id = this.toTaskId())
private fun IRequest?.requestId() = this?.requestId?.let { ComTaskRequestId(it) } ?: ComTaskRequestId.NONE
private fun String?.toProductId() = this?.let { ComTaskProductId(it) } ?: ComTaskProductId.NONE

private fun TaskDebug?.transportToWorkMode(): ComTaskWorkMode = when (this?.mode) {
    TaskRequestDebugMode.PROD -> ComTaskWorkMode.PROD
    TaskRequestDebugMode.TEST -> ComTaskWorkMode.TEST
    TaskRequestDebugMode.STUB -> ComTaskWorkMode.STUB
    null -> ComTaskWorkMode.PROD
}

private fun TaskDebug?.transportToStubCase(): ComTaskStubs = when (this?.stub) {
    TaskRequestDebugStubs.SUCCESS -> ComTaskStubs.SUCCESS
    TaskRequestDebugStubs.NOT_FOUND -> ComTaskStubs.NOT_FOUND
    TaskRequestDebugStubs.BAD_ID -> ComTaskStubs.BAD_ID
    TaskRequestDebugStubs.BAD_TITLE -> ComTaskStubs.BAD_TITLE
    TaskRequestDebugStubs.BAD_DATE_CREATE -> ComTaskStubs.BAD_DATE_CREATE
    TaskRequestDebugStubs.BAD_DATE_COMPLETION -> ComTaskStubs.BAD_DATE_COMPLETION
    TaskRequestDebugStubs.BAD_TASK_STATUS -> ComTaskStubs.BAD_TASK_STATUS
    TaskRequestDebugStubs.BAD_DESCRIPTION -> ComTaskStubs.BAD_DESCRIPTION
    TaskRequestDebugStubs.BAD_VISIBILITY -> ComTaskStubs.BAD_VISIBILITY
    TaskRequestDebugStubs.CANNOT_DELETE -> ComTaskStubs.CANNOT_DELETE
    TaskRequestDebugStubs.BAD_SEARCH_STRING -> ComTaskStubs.BAD_SEARCH_STRING
    null -> ComTaskStubs.NONE
}

fun ComTaskContext.fromTransport(request: TaskCreateRequest) {
    command = ComTaskCommand.CREATE
    requestId = request.requestId()
    taskRequest = request.task?.toInternal() ?: ComTask()
    workMode = request.debug.transportToWorkMode()
    stubCase = request.debug.transportToStubCase()
}

fun ComTaskContext.fromTransport(request: TaskReadRequest) {
    command = ComTaskCommand.READ
    requestId = request.requestId()
    taskRequest = request.task?.id.toTaskWithId()
    workMode = request.debug.transportToWorkMode()
    stubCase = request.debug.transportToStubCase()
}

fun ComTaskContext.fromTransport(request: TaskUpdateRequest) {
    command = ComTaskCommand.UPDATE
    requestId = request.requestId()
    taskRequest = request.task?.toInternal() ?: ComTask()
    workMode = request.debug.transportToWorkMode()
    stubCase = request.debug.transportToStubCase()
}

fun ComTaskContext.fromTransport(request: TaskDeleteRequest) {
    command = ComTaskCommand.DELETE
    requestId = request.requestId()
    taskRequest = request.task?.id.toTaskWithId()
    workMode = request.debug.transportToWorkMode()
    stubCase = request.debug.transportToStubCase()
}

fun ComTaskContext.fromTransport(request: TaskSearchRequest) {
    command = ComTaskCommand.SEARCH
    requestId = request.requestId()
    taskFilterRequest = request.taskFilter.toInternal()
    workMode = request.debug.transportToWorkMode()
    stubCase = request.debug.transportToStubCase()
}

private fun TaskSearchFilter?.toInternal(): ComTaskFilter = ComTaskFilter(
    searchString = this?.searchString ?: ""
)

private fun TaskCreateObject.toInternal(): ComTask = ComTask(
    title = this.title ?: "",
    description = this.description ?: "",
    datecreate = this.datecreate ?: "",
    visibility = this.visibility.fromTransport(),
    productId = this.productId.toProductId()
)

private fun TaskUpdateObject.toInternal(): ComTask = ComTask(
    id = this.id.toTaskId(),
    title = this.title ?: "",
    description = this.description ?: "",
    datecreate = this.datecreate ?: "",
    visibility = this.visibility.fromTransport(),
    productId = this.productId.toProductId()
)
private fun TaskVisibility?.fromTransport(): ComTaskVisibility = when (this) {
    TaskVisibility.PUBLIC -> ComTaskVisibility.VISIBLE_PUBLIC
    TaskVisibility.OWNER_ONLY -> ComTaskVisibility.VISIBLE_TO_OWNER
    TaskVisibility.REGISTERED_ONLY -> ComTaskVisibility.VISIBLE_TO_GROUP
    null -> ComTaskVisibility.NONE
}