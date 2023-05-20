package ru.rifnur.tasktracker.biz.validation

import ru.rifnur.tasktracker.common.ComTaskContext
import ru.rifnur.tasktracker.common.helpers.errorValidation
import ru.rifnur.tasktracker.common.helpers.fail
import ru.rifnur.tasktracker.cor.ICorChainDsl
import ru.rifnur.tasktracker.cor.worker


// TODO-validation-7: пример обработки ошибки в рамках бизнес-цепочки
fun ICorChainDsl<ComTaskContext>.validateDescriptionHasContent(title: String) = worker {
    this.title = title
    val regExp = Regex("\\p{L}")
    on { taskValidating.description.isNotEmpty() && ! taskValidating.description.contains(regExp) }
    handle {
        fail(
            errorValidation(
            field = "description",
            violationCode = "noContent",
            description = "field must contain letters"
        )
        )
    }
}
