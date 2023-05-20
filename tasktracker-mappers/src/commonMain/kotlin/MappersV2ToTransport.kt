package ru.rifnur.tasktracker.mappers.v2

import ru.rifnur.tasktracker.api.v2.models.*
import ru.rifnur.tasktracker.common.ComTaskContext
import ru.rifnur.tasktracker.common.models.*
import ru.rifnur.tasktracker.mappers.v2.exceptions.UnknownComTaskCommand

fun ComTaskContext.toTransportTask(): IResponse = when (val cmd = command) {
    ComTaskCommand.CREATE -> toTransportCreate()
    ComTaskCommand.READ -> toTransportRead()
    ComTaskCommand.UPDATE -> toTransportUpdate()
    ComTaskCommand.DELETE -> toTransportDelete()
    ComTaskCommand.SEARCH -> toTransportSearch()
    ComTaskCommand.NONE -> throw UnknownComTaskCommand(cmd)
}

fun ComTaskContext.toTransportCreate() = TaskCreateResponse(
    requestId = this.requestId.asString().takeIf { it.isNotBlank() },
    result = if (state == ComTaskState.RUNNING) ResponseResult.SUCCESS else ResponseResult.ERROR,
    errors = errors.toTransportErrors(),
    task = taskResponse.toTransportTask()
)

fun ComTaskContext.toTransportRead() = TaskReadResponse(
    requestId = this.requestId.asString().takeIf { it.isNotBlank() },
    result = if (state == ComTaskState.RUNNING) ResponseResult.SUCCESS else ResponseResult.ERROR,
    errors = errors.toTransportErrors(),
    task = taskResponse.toTransportTask()
)

fun ComTaskContext.toTransportUpdate() = TaskUpdateResponse(
    requestId = this.requestId.asString().takeIf { it.isNotBlank() },
    result = if (state == ComTaskState.RUNNING) ResponseResult.SUCCESS else ResponseResult.ERROR,
    errors = errors.toTransportErrors(),
    task = taskResponse.toTransportTask()
)

fun ComTaskContext.toTransportDelete() = TaskDeleteResponse(
    requestId = this.requestId.asString().takeIf { it.isNotBlank() },
    result = if (state == ComTaskState.RUNNING) ResponseResult.SUCCESS else ResponseResult.ERROR,
    errors = errors.toTransportErrors(),
    task = taskResponse.toTransportTask()
)

fun ComTaskContext.toTransportSearch() = TaskSearchResponse(
    requestId = this.requestId.asString().takeIf { it.isNotBlank() },
    result = if (state == ComTaskState.RUNNING) ResponseResult.SUCCESS else ResponseResult.ERROR,
    errors = errors.toTransportErrors(),
    tasks = tasksResponse.toTransportTask()
)

fun List<ComTask>.toTransportTask(): List<TaskResponseObject>? = this
    .map { it.toTransportTask() }
    .toList()
    .takeIf { it.isNotEmpty() }

private fun ComTask.toTransportTask(): TaskResponseObject = TaskResponseObject(
    id = id.takeIf { it != ComTaskId.NONE }?.asString(),
    title = title.takeIf { it.isNotBlank() },
    description = description.takeIf { it.isNotBlank() },
    datecreate = datecreate.takeIf { it.isNotBlank() },
    ownerId = ownerId.takeIf { it != ComTaskUserId.NONE }?.asString(),
    visibility = visibility.toTransportTask(),
    permissions = permissionsClient.toTransportTask(),
    productId = productId.takeIf { it != ComTaskProductId.NONE }?.asString(),
)

private fun Set<ComTaskPermissionClient>.toTransportTask(): Set<TaskPermissions>? = this
    .map { it.toTransportTask() }
    .toSet()
    .takeIf { it.isNotEmpty() }

fun ComTaskContext.toTransportInit() = TaskInitResponse(
    requestId = this.requestId.asString().takeIf { it.isNotBlank() },
    result = if (errors.isEmpty()) ResponseResult.SUCCESS else ResponseResult.ERROR,
    errors = errors.toTransportErrors(),
)

private fun ComTaskPermissionClient.toTransportTask() = when (this) {
    ComTaskPermissionClient.READ -> TaskPermissions.READ
    ComTaskPermissionClient.UPDATE -> TaskPermissions.UPDATE
    ComTaskPermissionClient.MAKE_VISIBLE_OWNER -> TaskPermissions.MAKE_VISIBLE_OWN
    ComTaskPermissionClient.MAKE_VISIBLE_GROUP -> TaskPermissions.MAKE_VISIBLE_GROUP
    ComTaskPermissionClient.MAKE_VISIBLE_PUBLIC -> TaskPermissions.MAKE_VISIBLE_PUBLIC
    ComTaskPermissionClient.DELETE -> TaskPermissions.DELETE
}

private fun ComTaskVisibility.toTransportTask(): TaskVisibility? = when (this) {
    ComTaskVisibility.VISIBLE_PUBLIC -> TaskVisibility.PUBLIC
    ComTaskVisibility.VISIBLE_TO_GROUP -> TaskVisibility.REGISTERED_ONLY
    ComTaskVisibility.VISIBLE_TO_OWNER -> TaskVisibility.OWNER_ONLY
    ComTaskVisibility.NONE -> null
}
private fun List<ComTaskError>.toTransportErrors(): List<Error>? = this
    .map { it.toTransportTask() }
    .toList()
    .takeIf { it.isNotEmpty() }

private fun ComTaskError.toTransportTask() = Error(
    code = code.takeIf { it.isNotBlank() },
    group = group.takeIf { it.isNotBlank() },
    field = field.takeIf { it.isNotBlank() },
    message = message.takeIf { it.isNotBlank() },
)
