package ru.rifnur.tasktracker.common.helpers

import ru.rifnur.tasktracker.common.ComTaskContext
import ru.rifnur.tasktracker.common.models.ComTaskCommand

fun ComTaskContext.isUpdatableCommand() =
    this.command in listOf(ComTaskCommand.CREATE, ComTaskCommand.UPDATE, ComTaskCommand.DELETE)
