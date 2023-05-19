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
class TaskCreateStubTest {

    private val processor = ComTaskProcessor()
    val id = ComTaskId("666")
    val title = "title 666"
    val description = "desc 666"
    val visibility = ComTaskVisibility.VISIBLE_PUBLIC

    @Test
    fun create() = runTest {

        val ctx = ComTaskContext(
            command = ComTaskCommand.CREATE,
            state = ComTaskState.NONE,
            workMode = ComTaskWorkMode.STUB,
            stubCase = ComTaskStubs.SUCCESS,
            taskRequest = ComTask(
                id = id,
                title = title,
                description = description,
                visibility = visibility,
            ),
        )
        processor.exec(ctx)
        assertEquals(ComTaskStub.get().id, ctx.taskResponse.id)
        assertEquals(title, ctx.taskResponse.title)
        assertEquals(description, ctx.taskResponse.description)
        assertEquals(visibility, ctx.taskResponse.visibility)
    }

    @Test
    fun badTitle() = runTest {
        val ctx = ComTaskContext(
            command = ComTaskCommand.CREATE,
            state = ComTaskState.NONE,
            workMode = ComTaskWorkMode.STUB,
            stubCase = ComTaskStubs.BAD_TITLE,
            taskRequest = ComTask(
                id = id,
                title = "",
                description = description,
                visibility = visibility,
            ),
        )
        processor.exec(ctx)
        assertEquals(ComTask(), ctx.taskResponse)
        assertEquals("title", ctx.errors.firstOrNull()?.field)
        assertEquals("validation", ctx.errors.firstOrNull()?.group)
    }
    @Test
    fun badDescription() = runTest {
        val ctx = ComTaskContext(
            command = ComTaskCommand.CREATE,
            state = ComTaskState.NONE,
            workMode = ComTaskWorkMode.STUB,
            stubCase = ComTaskStubs.BAD_DESCRIPTION,
            taskRequest = ComTask(
                id = id,
                title = title,
                description = "",
                visibility = visibility,
            ),
        )
        processor.exec(ctx)
        assertEquals(ComTask(), ctx.taskResponse)
        assertEquals("description", ctx.errors.firstOrNull()?.field)
        assertEquals("validation", ctx.errors.firstOrNull()?.group)
    }

    @Test
    fun databaseError() = runTest {
        val ctx = ComTaskContext(
            command = ComTaskCommand.CREATE,
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
            command = ComTaskCommand.CREATE,
            state = ComTaskState.NONE,
            workMode = ComTaskWorkMode.STUB,
            stubCase = ComTaskStubs.BAD_ID,
            taskRequest = ComTask(
                id = id,
                title = title,
                description = description,
                visibility = visibility,
            ),
        )
        processor.exec(ctx)
        assertEquals(ComTask(), ctx.taskResponse)
        assertEquals("stub", ctx.errors.firstOrNull()?.field)
        assertEquals("validation", ctx.errors.firstOrNull()?.group)
    }
}
