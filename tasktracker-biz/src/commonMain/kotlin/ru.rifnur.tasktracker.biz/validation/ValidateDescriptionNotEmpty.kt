package ru.rifnur.tasktracker.biz.validation

import ru.rifnur.tasktracker.common.ComTaskContext
import ru.rifnur.tasktracker.common.helpers.errorValidation
import ru.rifnur.tasktracker.common.helpers.fail
import ru.rifnur.tasktracker.cor.ICorChainDsl
import ru.rifnur.tasktracker.cor.worker

fun ICorChainDsl<ComTaskContext>.validateDescriptionNotEmpty(title: String) = worker {
    this.title = title
    on { taskValidating.description.isEmpty() }
    handle {
        fail(
            errorValidation(
            field = "description",
            violationCode = "empty",
            description = "field must not be empty"
        )
        )
    }
}
