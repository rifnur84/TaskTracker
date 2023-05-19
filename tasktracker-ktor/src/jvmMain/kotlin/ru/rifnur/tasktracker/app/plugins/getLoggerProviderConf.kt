package ru.rifnur.tasktracker.app.plugins

import io.ktor.server.application.*
import ru.rifnur.tasktracker.logging.common.MpLoggerProvider
import ru.rifnur.tasktracker.logging.jvm.mpLoggerLogback
import ru.rifnur.tasktracker.logging.kermit.mpLoggerKermit

actual fun Application.getLoggerProviderConf(): MpLoggerProvider =
    when (val mode = environment.config.propertyOrNull("ktor.logger")?.getString()) {
        "kmp" -> MpLoggerProvider { mpLoggerKermit(it) }
        "logback", null -> MpLoggerProvider { mpLoggerLogback(it) }
        else -> throw Exception("Logger $mode is not allowed. Additted values are kmp and logback")
    }