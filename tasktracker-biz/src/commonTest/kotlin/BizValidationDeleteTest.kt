package ru.rifnur.tasktracker.biz.validation

import kotlinx.coroutines.ExperimentalCoroutinesApi
import ru.rifnur.tasktracker.biz.ComTaskProcessor
import ru.rifnur.tasktracker.common.models.ComTaskCommand
import kotlin.test.Test

@OptIn(ExperimentalCoroutinesApi::class)
class BizValidationDeleteTest {

    private val command = ComTaskCommand.DELETE
    private val processor by lazy { ComTaskProcessor() }

    @Test fun correctId() = validationIdCorrect(command, processor)
    @Test fun trimId() = validationIdTrim(command, processor)
    @Test fun emptyId() = validationIdEmpty(command, processor)
    @Test fun badFormatId() = validationIdFormat(command, processor)


}

