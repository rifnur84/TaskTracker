package ru.rifnur.tasktracker.stubs

import ru.rifnur.tasktracker.common.models.ComTask
import ru.rifnur.tasktracker.common.models.ComTaskId
import ru.rifnur.tasktracker.stubs.ComTaskStubPrimer.Task_Zadanie1



object ComTaskStub {
    fun get(): ComTask = Task_Zadanie1.copy()

    fun prepareResult(block: ComTask.() -> Unit): ComTask = get().apply(block)

    fun prepareSearchList(filter: String) = listOf(
        comTaskDemand("1", filter),
        comTaskDemand("2", filter),
        comTaskDemand("3", filter),

    )

    private fun comTaskDemand(id: String, filter: String) =
        comTask(Task_Zadanie1, id = id, filter = filter)

    private fun comTask(base: ComTask, id: String, filter: String
    ) = base.copy(
        id = ComTaskId(id),
        title = "$filter $id",
        description = "desc $filter $id",
    )

}
