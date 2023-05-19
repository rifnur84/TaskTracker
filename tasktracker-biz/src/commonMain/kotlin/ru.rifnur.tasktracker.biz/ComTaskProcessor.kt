package ru.rifnur.tasktracker.biz

import ru.rifnur.tasktracker.biz.groups.operation
import ru.rifnur.tasktracker.biz.statemachine.computeTaskState
import ru.rifnur.tasktracker.biz.validation.*
import ru.rifnur.tasktracker.biz.workers.*
import ru.rifnur.tasktracker.common.ComTaskContext
import ru.rifnur.tasktracker.common.models.ComTaskCommand
import ru.rifnur.tasktracker.common.models.ComTaskId
import ru.rifnur.tasktracker.cor.rootChain
import ru.rifnur.tasktracker.cor.worker
import ru.rifnur.tasktracker.marketplace.biz.validation.validateTitleHasContent
import ru.rifnur.tasktracker.biz.groups.stubs
import ru.rifnur.tasktracker.marketplace.biz.validation.validateIdNotEmpty
import ru.rifnur.tasktracker.marketplace.biz.validation.validateTitleNotEmpty

class ComTaskProcessor {
    suspend fun exec(ctx: ComTaskContext) = BusinessChain.exec(ctx)

    companion object {
        private val BusinessChain = rootChain<ComTaskContext> {
            initStatus("Инициализация статуса")

            operation("Создание объявления", ComTaskCommand.CREATE) {
                stubs("Обработка стабов") {
                    stubCreateSuccess("Имитация успешной обработки")
                    stubValidationBadTitle("Имитация ошибки валидации заголовка")
                    stubValidationBadDescription("Имитация ошибки валидации описания")
                    stubDbError("Имитация ошибки работы с БД")
                    stubNoCase("Ошибка: запрошенный стаб недопустим")
                }
                validation {
                    worker("Копируем поля в taskValidating") { taskValidating = taskRequest.deepCopy() }
                    worker("Очистка id") { taskValidating.id = ComTaskId.NONE }
                    worker("Очистка заголовка") { taskValidating.title = taskValidating.title.trim() }
                    worker("Очистка описания") { taskValidating.description = taskValidating.description.trim() }
                    validateTitleNotEmpty("Проверка, что заголовок не пуст")
                    validateTitleHasContent("Проверка символов")
                    validateDescriptionNotEmpty("Проверка, что описание не пусто")
                    validateDescriptionHasContent("Проверка символов")

                    finishTaskValidation("Завершение проверок")
                }
            }
            operation("Получить объявление", ComTaskCommand.READ) {
                stubs("Обработка стабов") {
                    stubReadSuccess("Имитация успешной обработки")
                    stubValidationBadId("Имитация ошибки валидации id")
                    stubDbError("Имитация ошибки работы с БД")
                    stubNoCase("Ошибка: запрошенный стаб недопустим")
                }
                validation {
                    worker("Копируем поля в taskValidating") { taskValidating = taskRequest.deepCopy() }
                    worker("Очистка id") { taskValidating.id = ComTaskId(taskValidating.id.asString().trim()) }
                    validateIdNotEmpty("Проверка на непустой id")
                    validateIdProperFormat("Проверка формата id")

                    finishTaskValidation("Успешное завершение процедуры валидации")
                }
                computeTaskState("Вычисление состояния объявления")
            }
            operation("Изменить объявление", ComTaskCommand.UPDATE) {
                stubs("Обработка стабов") {
                    stubUpdateSuccess("Имитация успешной обработки")
                    stubValidationBadId("Имитация ошибки валидации id")
                    stubValidationBadTitle("Имитация ошибки валидации заголовка")
                    stubValidationBadDescription("Имитация ошибки валидации описания")
                    stubDbError("Имитация ошибки работы с БД")
                    stubNoCase("Ошибка: запрошенный стаб недопустим")
                }
                validation {
                    worker("Копируем поля в taskValidating") { taskValidating = taskRequest.deepCopy() }
                    worker("Очистка id") { taskValidating.id = ComTaskId(taskValidating.id.asString().trim()) }
                    worker("Очистка заголовка") { taskValidating.title = taskValidating.title.trim() }
                    worker("Очистка описания") { taskValidating.description = taskValidating.description.trim() }
                    validateIdNotEmpty("Проверка на непустой id")
                    validateIdProperFormat("Проверка формата id")
                    validateTitleNotEmpty("Проверка на непустой заголовок")
                    validateTitleHasContent("Проверка на наличие содержания в заголовке")
                    validateDescriptionNotEmpty("Проверка на непустое описание")
                    validateDescriptionHasContent("Проверка на наличие содержания в описании")

                    finishTaskValidation("Успешное завершение процедуры валидации")
                }
            }
            operation("Удалить объявление", ComTaskCommand.DELETE) {
                stubs("Обработка стабов") {
                    stubDeleteSuccess("Имитация успешной обработки")
                    stubValidationBadId("Имитация ошибки валидации id")
                    stubDbError("Имитация ошибки работы с БД")
                    stubNoCase("Ошибка: запрошенный стаб недопустим")
                }
                validation {
                    worker("Копируем поля в taskValidating") {
                        taskValidating = taskRequest.deepCopy() }
                    worker("Очистка id") { taskValidating.id = ComTaskId(taskValidating.id.asString().trim()) }
                    validateIdNotEmpty("Проверка на непустой id")
                    validateIdProperFormat("Проверка формата id")
                    finishTaskValidation("Успешное завершение процедуры валидации")
                }
            }
            operation("Поиск объявлений", ComTaskCommand.SEARCH) {
                stubs("Обработка стабов") {
                    stubSearchSuccess("Имитация успешной обработки")
                    stubValidationBadId("Имитация ошибки валидации id")
                    stubDbError("Имитация ошибки работы с БД")
                    stubNoCase("Ошибка: запрошенный стаб недопустим")
                }
                validation {
                    worker("Копируем поля в taskFilterValidating") { taskFilterValidating = taskFilterRequest.copy() }

                    finishTaskFilterValidation("Успешное завершение процедуры валидации")
                }
            }

        }.build()
    }
}

