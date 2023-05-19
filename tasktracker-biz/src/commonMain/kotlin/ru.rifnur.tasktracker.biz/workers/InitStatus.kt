package ru.rifnur.tasktracker.biz.workers

import ru.rifnur.tasktracker.common.ComTaskContext
import ru.rifnur.tasktracker.common.models.ComTaskState
import ru.rifnur.tasktracker.cor.ICorChainDsl
import ru.rifnur.tasktracker.cor.worker

fun ICorChainDsl<ComTaskContext>.initStatus(title: String) = worker() {
    this.title = title
    on { state == ComTaskState.NONE }
    handle { state = ComTaskState.RUNNING }
}
