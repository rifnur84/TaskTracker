package ru.rifnur.tasktracker.biz.validation.stub

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import ru.rifnur.tasktracker.biz.ComTaskProcessor
import ru.rifnur.tasktracker.common.ComTaskContext
import ru.rifnur.tasktracker.common.models.*
import ru.rifnur.tasktracker.common.stubs.ComTaskStubs
import ru.rifnur.tasktracker.stubs.ComTaskStub
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class TaskDeleteStubTest {

    private val processor = ComTaskProcessor()
    val id = ComTaskId("666")

    @Test
    fun delete() = runTest {

        val ctx = ComTaskContext(
            command = ComTaskCommand.DELETE,
            state = ComTaskState.NONE,
            workMode = ComTaskWorkMode.STUB,
            stubCase = ComTaskStubs.SUCCESS,
            taskRequest = ComTask(
                id = id,
            ),
        )
        processor.exec(ctx)

        val stub = ComTaskStub.get()
        assertEquals(stub.id, ctx.taskResponse.id)
        assertEquals(stub.title, ctx.taskResponse.title)
        assertEquals(stub.description, ctx.taskResponse.description)
        assertEquals(stub.visibility, ctx.taskResponse.visibility)
    }

    @Test
    fun badId() = runTest {
        val ctx = ComTaskContext(
            command = ComTaskCommand.DELETE,
            state = ComTaskState.NONE,
            workMode = ComTaskWorkMode.STUB,
            stubCase = ComTaskStubs.BAD_ID,
            taskRequest = ComTask(),
        )
        processor.exec(ctx)
        assertEquals(ComTask(), ctx.taskResponse)
        assertEquals("id", ctx.errors.firstOrNull()?.field)
        assertEquals("validation", ctx.errors.firstOrNull()?.group)
    }

    @Test
    fun databaseError() = runTest {
        val ctx = ComTaskContext(
            command = ComTaskCommand.DELETE,
            state = ComTaskState.NONE,
            workMode = ComTaskWorkMode.STUB,
            stubCase = ComTaskStubs.DB_ERROR,
            taskRequest = ComTask(
                id = id,
            ),
        )
        processor.exec(ctx)
        assertEquals(ComTask(), ctx.taskResponse)
        assertEquals("internal", ctx.errors.firstOrNull()?.group)
    }

    @Test
    fun badNoCase() = runTest {
        val ctx = ComTaskContext(
            command = ComTaskCommand.DELETE,
            state = ComTaskState.NONE,
            workMode = ComTaskWorkMode.STUB,
            stubCase = ComTaskStubs.BAD_TITLE,
            taskRequest = ComTask(
                id = id,
            ),
        )
        processor.exec(ctx)
        assertEquals(ComTask(), ctx.taskResponse)
        assertEquals("stub", ctx.errors.firstOrNull()?.field)
    }
}
