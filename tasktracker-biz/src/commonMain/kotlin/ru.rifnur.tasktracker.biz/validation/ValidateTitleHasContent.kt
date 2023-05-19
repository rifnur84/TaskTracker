package ru.rifnur.tasktracker.marketplace.biz.validation

import ru.rifnur.tasktracker.common.ComTaskContext
import ru.rifnur.tasktracker.common.helpers.errorValidation
import ru.rifnur.tasktracker.common.helpers.fail
import ru.rifnur.tasktracker.cor.ICorChainDsl
import ru.rifnur.tasktracker.cor.worker


fun ICorChainDsl<ComTaskContext>.validateTitleHasContent(title: String) = worker {
    this.title = title
    val regExp = Regex("\\p{L}")
    on { taskValidating.title.isNotEmpty() && ! taskValidating.title.contains(regExp) }
    handle {
        fail(
            errorValidation(
            field = "title",
            violationCode = "noContent",
            description = "field must contain leters"
        )
        )
    }
}
