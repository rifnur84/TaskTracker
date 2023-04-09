package ru.rifnur.tasktracker.mappers.v2

import ru.rifnur.tasktracker.api.v2.models.*
import ru.rifnur.tasktracker.common.ComTaskContext
import ru.rifnur.tasktracker.common.models.*
import ru.rifnur.tasktracker.common.stubs.ComTaskStubs
import kotlin.test.Test
import kotlin.test.assertEquals

class MapperReadTest {
    @Test
    fun fromTransport() {
        val req = TaskReadRequest(
            requestId = "1234",
            debug = TaskDebug(
                mode = TaskRequestDebugMode.STUB,
                stub = TaskRequestDebugStubs.SUCCESS,
            ),
            task = TaskReadObject(
                id = "12345"
            ),
        )

        val context = ComTaskContext()
        context.fromTransport(req)


        assertEquals("12345", context.taskRequest.id.asString())

    }

    @Test
    fun toTransport() {
        val context = ComTaskContext(
            requestId = ComTaskRequestId("1234"),
            command = ComTaskCommand.READ,
            taskResponse = ComTask(
                id = ComTaskId("123456"),
            ),
            errors = mutableListOf(
                ComTaskError(
                    code = "err",
                    group = "request",
                    field = "title",
                    message = "wrong title",
                )
            ),
            state = ComTaskState.RUNNING,
        )

        val req = context.toTransportTask() as TaskReadResponse

        assertEquals("123456", req.task?.id)

    }
}
