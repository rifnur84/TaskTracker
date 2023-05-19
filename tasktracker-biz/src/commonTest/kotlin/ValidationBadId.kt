package ru.rifnur.tasktracker.biz.validation

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import ru.rifnur.tasktracker.biz.ComTaskProcessor
import ru.rifnur.tasktracker.common.ComTaskContext
import ru.rifnur.tasktracker.common.models.*
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

@OptIn(ExperimentalCoroutinesApi::class)
fun validationIdCorrect(command: ComTaskCommand, processor: ComTaskProcessor) = runTest {
    val ctx = ComTaskContext(
        command = command,
        state = ComTaskState.NONE,
        workMode = ComTaskWorkMode.TEST,
        taskRequest = ComTask(
            id = ComTaskId("123-234-abc-ABC"),
            title = "abc",
            description = "abc",
            visibility = ComTaskVisibility.VISIBLE_PUBLIC,
        ),
    )
    processor.exec(ctx)
    assertEquals(0, ctx.errors.size)
    assertNotEquals(ComTaskState.FAILING, ctx.state)
}

@OptIn(ExperimentalCoroutinesApi::class)
fun validationIdTrim(command: ComTaskCommand, processor: ComTaskProcessor) = runTest {
    val ctx = ComTaskContext(
        command = command,
        state = ComTaskState.NONE,
        workMode = ComTaskWorkMode.TEST,
        taskRequest = ComTask(
            id = ComTaskId(" \n\t 123-234-abc-ABC \n\t "),
            title = "abc",
            description = "abc",
            visibility = ComTaskVisibility.VISIBLE_PUBLIC,
        ),
    )
    processor.exec(ctx)
    assertEquals(0, ctx.errors.size)
    assertNotEquals(ComTaskState.FAILING, ctx.state)
}

@OptIn(ExperimentalCoroutinesApi::class)
fun validationIdEmpty(command: ComTaskCommand, processor: ComTaskProcessor) = runTest {
    val ctx = ComTaskContext(
        command = command,
        state = ComTaskState.NONE,
        workMode = ComTaskWorkMode.TEST,
        taskRequest = ComTask(
            id = ComTaskId(""),
            title = "abc",
            description = "abc",
            visibility = ComTaskVisibility.VISIBLE_PUBLIC,
        ),
    )
    processor.exec(ctx)
    assertEquals(1, ctx.errors.size)
    assertEquals(ComTaskState.FAILING, ctx.state)
    val error = ctx.errors.firstOrNull()
    assertEquals("id", error?.field)
    assertContains(error?.message ?: "", "id")
}

@OptIn(ExperimentalCoroutinesApi::class)
fun validationIdFormat(command: ComTaskCommand, processor: ComTaskProcessor) = runTest {
    val ctx = ComTaskContext(
        command = command,
        state = ComTaskState.NONE,
        workMode = ComTaskWorkMode.TEST,
        taskRequest = ComTask(
            id = ComTaskId("!@#\$%^&*(),.{}"),
            title = "abc",
            description = "abc",
            visibility = ComTaskVisibility.VISIBLE_PUBLIC,
        ),
    )
    processor.exec(ctx)
    assertEquals(1, ctx.errors.size)
    assertEquals(ComTaskState.FAILING, ctx.state)
    val error = ctx.errors.firstOrNull()
    assertEquals("id", error?.field)
    assertContains(error?.message ?: "", "id")
}
