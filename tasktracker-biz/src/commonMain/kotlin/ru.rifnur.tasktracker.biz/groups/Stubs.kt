package ru.rifnur.tasktracker.biz.groups

import ru.rifnur.tasktracker.common.ComTaskContext
import ru.rifnur.tasktracker.common.models.ComTaskState
import ru.rifnur.tasktracker.common.models.ComTaskWorkMode
import ru.rifnur.tasktracker.cor.ICorChainDsl
import ru.rifnur.tasktracker.cor.chain

fun ICorChainDsl<ComTaskContext>.stubs(title: String, block: ICorChainDsl<ComTaskContext>.() -> Unit) = chain {
    block()
    this.title = title
    on { workMode == ComTaskWorkMode.STUB && state == ComTaskState.RUNNING }
}
