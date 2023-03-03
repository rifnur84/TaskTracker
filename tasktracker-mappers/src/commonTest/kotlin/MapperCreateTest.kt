package ru.rifnur.tasktracker.mappers.v2

import ru.rifnur.tasktracker.api.v2.models.*
import ru.rifnur.tasktracker.common.ComTaskContext
import ru.rifnur.tasktracker.common.models.*
import ru.rifnur.tasktracker.common.stubs.ComTaskStubs
import kotlin.test.Test
import kotlin.test.assertEquals

class MapperCreateTest {
    @Test
    fun fromTransport() {
        val req = TaskCreateRequest(
            requestId = "1234",
            debug = TaskDebug(
                mode = TaskRequestDebugMode.STUB,
                stub = TaskRequestDebugStubs.SUCCESS,
            ),
            task = TaskCreateObject(
                title = "title",
                description = "desc",
                datecreate = "03.03.2023",
                visibility = TaskVisibility.PUBLIC,
            ),
        )

        val context = ComTaskContext()
        context.fromTransport(req)

        assertEquals(ComTaskStubs.SUCCESS, context.stubCase)
        assertEquals(ComTaskWorkMode.STUB, context.workMode)
        assertEquals("title", context.taskRequest.title)
        assertEquals(ComTaskVisibility.VISIBLE_PUBLIC, context.taskRequest.visibility)
    }

    @Test
    fun toTransport() {
        val context = ComTaskContext(
            requestId = ComTaskRequestId("1234"),
            command = ComTaskCommand.CREATE,
            taskResponse = ComTask(
                title = "title",
                description = "desc",
                datecreate = "03.03.2023",
                visibility = ComTaskVisibility.VISIBLE_PUBLIC,
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

        val req = context.toTransportTask() as TaskCreateResponse

        assertEquals("1234", req.requestId)
        assertEquals("title", req.task?.title)
        assertEquals("desc", req.task?.description)
        assertEquals("03.03.2023", req.task?.datecreate)
        assertEquals(TaskVisibility.PUBLIC, req.task?.visibility)
        assertEquals(1, req.errors?.size)
        assertEquals("err", req.errors?.firstOrNull()?.code)
        assertEquals("request", req.errors?.firstOrNull()?.group)
        assertEquals("title", req.errors?.firstOrNull()?.field)
        assertEquals("wrong title", req.errors?.firstOrNull()?.message)
    }
}
