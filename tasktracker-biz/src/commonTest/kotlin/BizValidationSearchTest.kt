package ru.rifnur.tasktracker.biz.validation

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import ru.rifnur.tasktracker.biz.ComTaskProcessor
import ru.rifnur.tasktracker.common.ComTaskContext
import ru.rifnur.tasktracker.common.models.*
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

@OptIn(ExperimentalCoroutinesApi::class)
class BizValidationSearchTest {

    private val command = ComTaskCommand.SEARCH
    private val processor by lazy { ComTaskProcessor() }

    @Test
    fun correctEmpty() = runTest {
        val ctx = ComTaskContext(
            command = command,
            state = ComTaskState.NONE,
            workMode = ComTaskWorkMode.TEST,
            taskFilterRequest = ComTaskFilter()
        )
        processor.exec(ctx)
        assertEquals(0, ctx.errors.size)
        assertNotEquals(ComTaskState.FAILING, ctx.state)
    }
}

