package ru.rifnur.tasktracker.biz.statemachine


import ru.rifnur.tasktracker.common.statemachine.SMTaskStates
import kotlin.time.Duration

data class SMTaskSignal(
    val state: SMTaskStates,
    val duration: Duration,
    val views: Int,
)
