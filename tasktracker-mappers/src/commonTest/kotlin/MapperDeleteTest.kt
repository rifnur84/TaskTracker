package ru.rifnur.tasktracker.mappers.v2

import ru.rifnur.tasktracker.api.v2.models.*
import ru.rifnur.tasktracker.common.ComTaskContext
import ru.rifnur.tasktracker.common.models.*
import kotlin.test.Test
import kotlin.test.assertEquals

class MapperDeleteTest {
    @Test
    fun fromTransport() {
        val req = TaskDeleteRequest(
            requestId = "1234",
            debug = TaskDebug(
                mode = TaskRequestDebugMode.STUB,
                stub = TaskRequestDebugStubs.SUCCESS,
            ),
            task = TaskDeleteObject(
                id = "12345",
                lock = "lock"
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
            command = ComTaskCommand.DELETE,
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

        val req = context.toTransportTask() as TaskDeleteResponse

        assertEquals("123456", req.task?.id)

    }
}
