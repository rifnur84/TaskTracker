package ru.rifnur.tasktracker.biz.workers

import ru.rifnur.tasktracker.common.ComTaskContext
import ru.rifnur.tasktracker.common.models.ComTaskId
import ru.rifnur.tasktracker.common.models.ComTaskState
import ru.rifnur.tasktracker.common.models.ComTaskVisibility
import ru.rifnur.tasktracker.common.stubs.ComTaskStubs
import ru.rifnur.tasktracker.cor.ICorChainDsl
import ru.rifnur.tasktracker.cor.worker
import ru.rifnur.tasktracker.stubs.ComTaskStub


fun ICorChainDsl<ComTaskContext>.stubUpdateSuccess(title: String) = worker {
    this.title = title
    on { stubCase == ComTaskStubs.SUCCESS && state == ComTaskState.RUNNING }
    handle {
        state = ComTaskState.FINISHING
        val stub = ComTaskStub.prepareResult {
            taskRequest.id.takeIf { it != ComTaskId.NONE }?.also { this.id = it }
            taskRequest.title.takeIf { it.isNotBlank() }?.also { this.title = it }
            taskRequest.description.takeIf { it.isNotBlank() }?.also { this.description = it }
            taskRequest.visibility.takeIf { it != ComTaskVisibility.NONE }?.also { this.visibility = it }
        }
        taskResponse = stub
    }
}
