package ru.rifnur.tasktracker.biz.statemachine

import ru.rifnur.tasktracker.common.statemachine.SMTaskStates


data class SMTransition(
    val state: SMTaskStates,
    val description: String,
)
