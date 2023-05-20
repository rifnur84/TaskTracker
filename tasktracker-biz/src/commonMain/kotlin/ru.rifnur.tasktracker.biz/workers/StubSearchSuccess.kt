package ru.rifnur.tasktracker.biz.workers

import ru.rifnur.tasktracker.common.ComTaskContext
import ru.rifnur.tasktracker.common.models.ComTaskState
import ru.rifnur.tasktracker.common.stubs.ComTaskStubs
import ru.rifnur.tasktracker.cor.ICorChainDsl
import ru.rifnur.tasktracker.cor.worker
import ru.rifnur.tasktracker.stubs.ComTaskStub


fun ICorChainDsl<ComTaskContext>.stubSearchSuccess(title: String) = worker {
    this.title = title
    on { stubCase == ComTaskStubs.SUCCESS && state == ComTaskState.RUNNING }
    handle {
        state = ComTaskState.FINISHING
        tasksResponse.addAll(ComTaskStub.prepareSearchList(taskFilterRequest.searchString))
    }
}
