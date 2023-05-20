package ru.rifnur.tasktracker.app.v2

import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import kotlinx.datetime.Clock
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import ru.rifnur.tasktracker.api.logs.mapper.toLog
import ru.rifnur.tasktracker.api.v2.apiV2Mapper
import ru.rifnur.tasktracker.api.v2.models.IRequest
import ru.rifnur.tasktracker.api.v2.models.IResponse
import ru.rifnur.tasktracker.app.ComTaskAppSettings
import ru.rifnur.tasktracker.common.ComTaskContext
import ru.rifnur.tasktracker.common.helpers.asComTaskError
import ru.rifnur.tasktracker.common.models.ComTaskCommand
import ru.rifnur.tasktracker.common.models.ComTaskState
import ru.rifnur.tasktracker.logging.common.IMpLogWrapper
import ru.rifnur.tasktracker.mappers.v2.fromTransport
import ru.rifnur.tasktracker.mappers.v2.toTransportTask

// TODO-validation-1: смотрим универсальную функцию обработки запроса (v2)
suspend inline fun <reified Q : IRequest, @Suppress("unused") reified R : IResponse> ApplicationCall.processV2(
    appSettings: ComTaskAppSettings,
    logger: IMpLogWrapper,
    logId: String,
    command: ComTaskCommand? = null,
) {
    val ctx = ComTaskContext(
        timeStart = Clock.System.now(),
    )
    val processor = appSettings.processor
    try {
        logger.doWithLogging(id = logId) {
            val request = apiV2Mapper.decodeFromString<Q>(receiveText())
            ctx.fromTransport(request)
            logger.info(
                msg = "$command request is got",
                data = ctx.toLog("${logId}-got")
            )
            processor.exec(ctx)
            logger.info(
                msg = "$command request is handled",
                data = ctx.toLog("${logId}-handled")
            )
            respond(apiV2Mapper.encodeToString(ctx.toTransportTask()))
        }
    } catch (e: Throwable) {
        logger.doWithLogging(id = "${logId}-failure") {
            command?.also { ctx.command = it }
            logger.error(
                msg = "$command handling failed",
            )
            ctx.state = ComTaskState.FAILING
            ctx.errors.add(e.asComTaskError())
            processor.exec(ctx)
            respond(apiV2Mapper.encodeToString(ctx.toTransportTask()))
        }
    }
}
