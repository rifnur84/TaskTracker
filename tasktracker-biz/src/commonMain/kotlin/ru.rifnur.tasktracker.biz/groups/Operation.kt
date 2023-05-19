package ru.rifnur.tasktracker.biz.groups

import ru.rifnur.tasktracker.common.models.ComTaskState
import ru.rifnur.tasktracker.common.ComTaskContext
import ru.rifnur.tasktracker.common.models.ComTaskCommand
import ru.rifnur.tasktracker.cor.ICorChainDsl
import ru.rifnur.tasktracker.cor.chain

fun ICorChainDsl<ComTaskContext>.operation(title: String, command: ComTaskCommand, block: ICorChainDsl<ComTaskContext>.() -> Unit) = chain {
    block()
    this.title = title
    on { this.command == command && state == ComTaskState.RUNNING }
}
