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
import kotlin.test.assertTrue
import kotlin.test.fail

@OptIn(ExperimentalCoroutinesApi::class)
class TaskSearchStubTest {

    private val processor = ComTaskProcessor()
    val filter = ComTaskFilter(searchString = "bolt")

    @Test
    fun read() = runTest {

        val ctx = ComTaskContext(
            command = ComTaskCommand.SEARCH,
            state = ComTaskState.NONE,
            workMode = ComTaskWorkMode.STUB,
            stubCase = ComTaskStubs.SUCCESS,
            taskFilterRequest = filter,
        )
        processor.exec(ctx)
        assertTrue(ctx.tasksResponse.size > 1)
        val first = ctx.tasksResponse.firstOrNull() ?: fail("Empty response list")
        assertTrue(first.title.contains(filter.searchString))
        assertTrue(first.description.contains(filter.searchString))
        with (ComTaskStub.get()) {
            assertEquals(visibility, first.visibility)
        }
    }

    @Test
    fun badId() = runTest {
        val ctx = ComTaskContext(
            command = ComTaskCommand.SEARCH,
            state = ComTaskState.NONE,
            workMode = ComTaskWorkMode.STUB,
            stubCase = ComTaskStubs.BAD_ID,
            taskFilterRequest = filter,
        )
        processor.exec(ctx)
        assertEquals(ComTask(), ctx.taskResponse)
        assertEquals("id", ctx.errors.firstOrNull()?.field)
        assertEquals("validation", ctx.errors.firstOrNull()?.group)
    }

    @Test
    fun databaseError() = runTest {
        val ctx = ComTaskContext(
            command = ComTaskCommand.SEARCH,
            state = ComTaskState.NONE,
            workMode = ComTaskWorkMode.STUB,
            stubCase = ComTaskStubs.DB_ERROR,
            taskFilterRequest = filter,
        )
        processor.exec(ctx)
        assertEquals(ComTask(), ctx.taskResponse)
        assertEquals("internal", ctx.errors.firstOrNull()?.group)
    }

    @Test
    fun badNoCase() = runTest {
        val ctx = ComTaskContext(
            command = ComTaskCommand.SEARCH,
            state = ComTaskState.NONE,
            workMode = ComTaskWorkMode.STUB,
            stubCase = ComTaskStubs.BAD_TITLE,
            taskFilterRequest = filter,
        )
        processor.exec(ctx)
        assertEquals(ComTask(), ctx.taskResponse)
        assertEquals("stub", ctx.errors.firstOrNull()?.field)
    }
}
