package ru.rifnur.tasktracker.common.helpers

import ru.rifnur.tasktracker.common.ComTaskContext
import ru.rifnur.tasktracker.common.models.ComTaskError
import ru.rifnur.tasktracker.common.models.ComTaskState

fun Throwable.asComTaskError(
    code: String = "unknown",
    group: String = "exceptions",
    message: String = this.message ?: "",
) = ComTaskError(
    code = code,
    group = group,
    field = "",
    message = message,
    exception = this,
)

fun ComTaskContext.addError(vararg error: ComTaskError) = errors.addAll(error)

fun ComTaskContext.fail(error: ComTaskError) {
    addError(error)
    state = ComTaskState.FAILING
}
fun errorValidation(
    field: String,
    /**
     * Код, характеризующий ошибку. Не должен включать имя поля или указание на валидацию.
     * Например: empty, badSymbols, tooLong, etc
     */
    violationCode: String,
    description: String,
    level: ComTaskError.Level = ComTaskError.Level.ERROR,
) = ComTaskError(
    code = "validation-$field-$violationCode",
    field = field,
    group = "validation",
    message = "Validation error for field $field: $description",
    level = level,
)
