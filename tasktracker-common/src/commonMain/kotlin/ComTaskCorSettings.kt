package ru.rifnur.tasktracker.common

import ru.rifnur.tasktracker.logging.common.MpLoggerProvider

data class ComTaskCorSettings(
    val loggerProvider: MpLoggerProvider = MpLoggerProvider(),
) {
    companion object {
        val NONE = ComTaskCorSettings()
    }
}
