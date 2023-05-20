package ru.rifnur.tasktracker.biz.validation


import ru.rifnur.tasktracker.common.ComTaskContext
import ru.rifnur.tasktracker.common.models.ComTaskState
import ru.rifnur.tasktracker.cor.ICorChainDsl
import ru.rifnur.tasktracker.cor.worker

fun ICorChainDsl<ComTaskContext>.finishTaskValidation(title: String) = worker {
    this.title = title
    on { state == ComTaskState.RUNNING }
    handle {
        taskValidated = taskValidating
    }
}

fun ICorChainDsl<ComTaskContext>.finishTaskFilterValidation(title: String) = worker {
    this.title = title
    on { state == ComTaskState.RUNNING }
    handle {
        taskFilterValidated = taskFilterValidating
    }
}
