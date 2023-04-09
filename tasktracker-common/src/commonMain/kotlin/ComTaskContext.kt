package ru.rifnur.tasktracker.common

import kotlinx.datetime.Instant
import ru.rifnur.tasktracker.common.models.*
import ru.rifnur.tasktracker.common.stubs.ComTaskStubs

data class ComTaskContext(
    var command: ComTaskCommand = ComTaskCommand.NONE,
    var state: ComTaskState = ComTaskState.NONE,
    val errors: MutableList<ComTaskError> = mutableListOf(),

    var workMode: ComTaskWorkMode = ComTaskWorkMode.PROD,
    var stubCase: ComTaskStubs = ComTaskStubs.NONE,

    var requestId: ComTaskRequestId = ComTaskRequestId.NONE,
    var timeStart: Instant = Instant.NONE,
    var taskRequest: ComTask = ComTask(),
    var taskFilterRequest: ComTaskFilter = ComTaskFilter(),
    var taskResponse: ComTask = ComTask(),
    var tasksResponse: MutableList<ComTask> = mutableListOf(),
)
