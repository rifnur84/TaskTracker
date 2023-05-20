package ru.rifnur.tasktracker.biz.statemachine

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import ru.rifnur.tasktracker.common.ComTaskContext
import ru.rifnur.tasktracker.common.NONE
import ru.rifnur.tasktracker.common.models.ComTaskState
import ru.rifnur.tasktracker.common.statemachine.SMTaskStates
import ru.rifnur.tasktracker.cor.ICorChainDsl
import ru.rifnur.tasktracker.cor.worker
//import ru.rifnur.tasktracker.biz.statemachine.SMTaskStateResolver

import kotlin.reflect.KClass

private val machine = SMTaskStateResolver()
private val clazz: KClass<*> = ICorChainDsl<ComTaskContext>::computeTaskState::class

fun ICorChainDsl<ComTaskContext>.computeTaskState(title: String) = worker {
    this.title = title
    this.description = "Вычисление состояния объявления"
    on { state == ComTaskState.RUNNING }
    handle {
        val log = settings.loggerProvider.logger(clazz)
        val timeNow = Clock.System.now()
        val task = taskValidated
        val prevState = task.taskState
        val timePublished = task.timePublished.takeIf { it != Instant.NONE } ?: timeNow
        val signal = SMTaskSignal(
            state = prevState.takeIf { it != SMTaskStates.NONE } ?: SMTaskStates.NEW,
            duration = timeNow - timePublished,
            views = task.views,
        )
        val transition = machine.resolve(signal)
        if (transition.state != prevState) {
            log.info("New ad state transition: ${transition.description}")
        }
        task.taskState = transition.state
    }
}
