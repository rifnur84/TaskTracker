package ru.rifnur.tasktracker.biz.workers

import ru.rifnur.tasktracker.common.ComTaskContext
import ru.rifnur.tasktracker.common.models.ComTaskError
import ru.rifnur.tasktracker.common.models.ComTaskState
import ru.rifnur.tasktracker.common.stubs.ComTaskStubs
import ru.rifnur.tasktracker.cor.ICorChainDsl
import ru.rifnur.tasktracker.cor.worker


fun ICorChainDsl<ComTaskContext>.stubValidationBadDescription(title: String) = worker {
    this.title = title
    on { stubCase == ComTaskStubs.BAD_DESCRIPTION && state == ComTaskState.RUNNING }
    handle {
        state = ComTaskState.FAILING
        this.errors.add(
            ComTaskError(
                group = "validation",
                code = "validation-description",
                field = "description",
                message = "Wrong description field"
            )
        )
    }
}
