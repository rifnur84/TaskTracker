package ru.rifnur.tasktracker.biz.validation

import ru.rifnur.tasktracker.common.ComTaskContext
import ru.rifnur.tasktracker.common.models.ComTaskState
import ru.rifnur.tasktracker.cor.ICorChainDsl
import ru.rifnur.tasktracker.cor.chain

fun ICorChainDsl<ComTaskContext>.validation(block: ICorChainDsl<ComTaskContext>.() -> Unit) = chain {
    block()
    title = "Валидация"

    on { state == ComTaskState.RUNNING }
}
