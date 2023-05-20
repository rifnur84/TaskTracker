package ru.rifnur.tasktracker.biz.workers

import ru.rifnur.tasktracker.common.ComTaskContext
import ru.rifnur.tasktracker.common.models.ComTaskError
import ru.rifnur.tasktracker.common.models.ComTaskState
import ru.rifnur.tasktracker.cor.ICorChainDsl
import ru.rifnur.tasktracker.cor.worker
import ru.rifnur.tasktracker.common.helpers.fail

fun ICorChainDsl<ComTaskContext>.stubNoCase(title: String) = worker {
    this.title = title
    on { state == ComTaskState.RUNNING }
    handle {
        fail(
            ComTaskError(
                code = "validation",
                field = "stub",
                group = "validation",
                message = "Wrong stub case is requested: ${stubCase.name}"
            )
        )
    }
}
