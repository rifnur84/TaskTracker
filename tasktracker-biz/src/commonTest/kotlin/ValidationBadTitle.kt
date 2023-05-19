package ru.rifnur.tasktracker.biz.validation

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import ru.rifnur.tasktracker.biz.ComTaskProcessor
import ru.rifnur.tasktracker.common.ComTaskContext
import ru.rifnur.tasktracker.common.models.*
import ru.rifnur.tasktracker.stubs.ComTaskStub
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

private val stub = ComTaskStub.get()

@OptIn(ExperimentalCoroutinesApi::class)
fun validationTitleCorrect(command: ComTaskCommand, processor: ComTaskProcessor) = runTest {
    val ctx = ComTaskContext(
        command = command,
        state = ComTaskState.NONE,
        workMode = ComTaskWorkMode.TEST,
        taskRequest = ComTask(
            id = stub.id,
            title = "abc",
            description = "abc",
            visibility = ComTaskVisibility.VISIBLE_PUBLIC,
        ),
    )
    processor.exec(ctx)
    assertEquals(0, ctx.errors.size)
    assertNotEquals(ComTaskState.FAILING, ctx.state)
    assertEquals("abc", ctx.taskValidated.title)
}

@OptIn(ExperimentalCoroutinesApi::class)
fun validationTitleTrim(command: ComTaskCommand, processor: ComTaskProcessor) = runTest {
    val ctx = ComTaskContext(
        command = command,
        state = ComTaskState.NONE,
        workMode = ComTaskWorkMode.TEST,
        taskRequest = ComTask(
            id = stub.id,
            title = " \n\t abc \t\n ",
            description = "abc",
            visibility = ComTaskVisibility.VISIBLE_PUBLIC,
        ),
    )
    processor.exec(ctx)
    assertEquals(0, ctx.errors.size)
    assertNotEquals(ComTaskState.FAILING, ctx.state)
    assertEquals("abc", ctx.taskValidated.title)
}

@OptIn(ExperimentalCoroutinesApi::class)
fun validationTitleEmpty(command: ComTaskCommand, processor: ComTaskProcessor) = runTest {
    val ctx = ComTaskContext(
        command = command,
        state = ComTaskState.NONE,
        workMode = ComTaskWorkMode.TEST,
        taskRequest = ComTask(
            id = stub.id,
            title = "",
            description = "abc",
            visibility = ComTaskVisibility.VISIBLE_PUBLIC,
        ),
    )
    processor.exec(ctx)
    assertEquals(1, ctx.errors.size)
    assertEquals(ComTaskState.FAILING, ctx.state)
    val error = ctx.errors.firstOrNull()
    assertEquals("title", error?.field)
    assertContains(error?.message ?: "", "title")
}

@OptIn(ExperimentalCoroutinesApi::class)
fun validationTitleSymbols(command: ComTaskCommand, processor: ComTaskProcessor) = runTest {
    val ctx = ComTaskContext(
        command = command,
        state = ComTaskState.NONE,
        workMode = ComTaskWorkMode.TEST,
        taskRequest = ComTask(
            id = ComTaskId("123"),
            title = "!@#$%^&*(),.{}",
            description = "abc",
            visibility = ComTaskVisibility.VISIBLE_PUBLIC,
        ),
    )
    processor.exec(ctx)
    assertEquals(1, ctx.errors.size)
    assertEquals(ComTaskState.FAILING, ctx.state)
    val error = ctx.errors.firstOrNull()
    assertEquals("title", error?.field)
    assertContains(error?.message ?: "", "title")
}
