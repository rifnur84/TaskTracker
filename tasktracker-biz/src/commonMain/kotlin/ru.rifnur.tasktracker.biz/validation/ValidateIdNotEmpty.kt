package ru.rifnur.tasktracker.marketplace.biz.validation

import ru.rifnur.tasktracker.common.ComTaskContext
import ru.rifnur.tasktracker.common.helpers.errorValidation
import ru.rifnur.tasktracker.common.helpers.fail
import ru.rifnur.tasktracker.cor.ICorChainDsl
import ru.rifnur.tasktracker.cor.worker

fun ICorChainDsl<ComTaskContext>.validateIdNotEmpty(title: String) = worker {
    this.title = title
    on { taskValidating.id.asString().isEmpty() }
    handle {
        fail(
            errorValidation(
                field = "id",
                violationCode = "empty",
                description = "field must not be empty"
            )
        )
    }
}
