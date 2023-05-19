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
fun validationDescriptionCorrect(command: ComTaskCommand, processor: ComTaskProcessor) = runTest {
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
    assertEquals("abc", ctx.taskValidated.description)
}

@OptIn(ExperimentalCoroutinesApi::class)
fun validationDescriptionTrim(command: ComTaskCommand, processor: ComTaskProcessor) = runTest {
    val ctx = ComTaskContext(
        command = command,
        state = ComTaskState.NONE,
        workMode = ComTaskWorkMode.TEST,
        taskRequest = ComTask(
            id = stub.id,
            title = "abc",
            description = " \n\tabc \n\t",
            visibility = ComTaskVisibility.VISIBLE_PUBLIC,
        ),
    )
    processor.exec(ctx)
    assertEquals(0, ctx.errors.size)
    assertNotEquals(ComTaskState.FAILING, ctx.state)
    assertEquals("abc", ctx.taskValidated.description)
}

@OptIn(ExperimentalCoroutinesApi::class)
fun validationDescriptionEmpty(command: ComTaskCommand, processor: ComTaskProcessor) = runTest {
    val ctx = ComTaskContext(
        command = command,
        state = ComTaskState.NONE,
        workMode = ComTaskWorkMode.TEST,
        taskRequest = ComTask(
            id = stub.id,
            title = "abc",
            description = "",
            visibility = ComTaskVisibility.VISIBLE_PUBLIC,
        ),
    )
    processor.exec(ctx)
    assertEquals(1, ctx.errors.size)
    assertEquals(ComTaskState.FAILING, ctx.state)
    val error = ctx.errors.firstOrNull()
    assertEquals("description", error?.field)
    assertContains(error?.message ?: "", "description")
}

@OptIn(ExperimentalCoroutinesApi::class)
fun validationDescriptionSymbols(command: ComTaskCommand, processor: ComTaskProcessor) = runTest {
    val ctx = ComTaskContext(
        command = command,
        state = ComTaskState.NONE,
        workMode = ComTaskWorkMode.TEST,
        taskRequest = ComTask(
            id = stub.id,
            title = "abc",
            description = "!@#$%^&*(),.{}",
            visibility = ComTaskVisibility.VISIBLE_PUBLIC,
        ),
    )
    processor.exec(ctx)
    assertEquals(1, ctx.errors.size)
    assertEquals(ComTaskState.FAILING, ctx.state)
    val error = ctx.errors.firstOrNull()
    assertEquals("description", error?.field)
    assertContains(error?.message ?: "", "description")
}
