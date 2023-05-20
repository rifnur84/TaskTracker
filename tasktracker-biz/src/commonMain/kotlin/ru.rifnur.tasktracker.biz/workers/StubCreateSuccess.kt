package ru.rifnur.tasktracker.biz.workers

import ru.rifnur.tasktracker.common.ComTaskContext
import ru.rifnur.tasktracker.common.models.ComTaskState
import ru.rifnur.tasktracker.common.models.ComTaskVisibility
import ru.rifnur.tasktracker.common.stubs.ComTaskStubs
import ru.rifnur.tasktracker.stubs.ComTaskStub
import ru.rifnur.tasktracker.cor.ICorChainDsl
import ru.rifnur.tasktracker.cor.worker

fun ICorChainDsl<ComTaskContext>.stubCreateSuccess(title: String) = worker {
    this.title = title
    on { stubCase == ComTaskStubs.SUCCESS && state == ComTaskState.RUNNING }
    handle {
        state = ComTaskState.FINISHING
        val stub = ComTaskStub.prepareResult {
            taskRequest.title.takeIf { it.isNotBlank() }?.also { this.title = it }
            taskRequest.description.takeIf { it.isNotBlank() }?.also { this.description = it }
//            taskRequest.adType.takeIf { it != MkplDealSide.NONE }?.also { this.adType = it }
            taskRequest.visibility.takeIf { it != ComTaskVisibility.NONE }?.also { this.visibility = it }
        }
        taskResponse = stub
    }
}
