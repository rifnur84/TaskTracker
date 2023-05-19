package ru.rifnur.tasktracker.marketplace.biz.validation

import ru.rifnur.tasktracker.common.ComTaskContext
import ru.rifnur.tasktracker.common.helpers.errorValidation
import ru.rifnur.tasktracker.common.helpers.fail
import ru.rifnur.tasktracker.cor.ICorChainDsl
import ru.rifnur.tasktracker.cor.worker


// TODO-validation-4: смотрим пример COR DSL валидации
fun ICorChainDsl<ComTaskContext>.validateTitleNotEmpty(title: String) = worker {
    this.title = title
    on { taskValidating.title.isEmpty() }
    handle {
        fail(
            errorValidation(
            field = "title",
            violationCode = "empty",
            description = "field must not be empty"
        )
        )
    }
}
