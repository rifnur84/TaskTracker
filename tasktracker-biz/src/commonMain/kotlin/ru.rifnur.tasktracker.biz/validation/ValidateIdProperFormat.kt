package ru.rifnur.tasktracker.biz.validation

import ru.rifnur.tasktracker.common.ComTaskContext
import ru.rifnur.tasktracker.common.helpers.errorValidation
import ru.rifnur.tasktracker.common.helpers.fail
import ru.rifnur.tasktracker.common.models.ComTaskId
import ru.rifnur.tasktracker.cor.ICorChainDsl
import ru.rifnur.tasktracker.cor.worker


fun ICorChainDsl<ComTaskContext>.validateIdProperFormat(title: String) = worker {
    this.title = title

    // Может быть вынесен в MkplAdId для реализации различных форматов
    val regExp = Regex("^[0-9a-zA-Z-]+$")
    on { taskValidating.id != ComTaskId.NONE && ! taskValidating.id.asString().matches(regExp) }
    handle {
        val encodedId = taskValidating.id.asString()
            .replace("<", "&lt;")
            .replace(">", "&gt;")
        fail(
            errorValidation(
            field = "id",
            violationCode = "badFormat",
            description = "value $encodedId must contain only letters and numbers"
        )
        )
    }
}
