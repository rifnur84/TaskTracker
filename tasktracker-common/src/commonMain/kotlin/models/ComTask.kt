package ru.rifnur.tasktracker.common.models

import kotlinx.datetime.Instant
import ru.rifnur.tasktracker.common.NONE
import ru.rifnur.tasktracker.common.statemachine.SMTaskStates

data class ComTask(
    var id: ComTaskId = ComTaskId.NONE,
    var title: String = "",
    var description: String = "",
    var datecreate: String= "",
    var ownerId: ComTaskUserId = ComTaskUserId.NONE,
    var taskState: SMTaskStates = SMTaskStates.NONE,
    var views: Int = 0,
    var timePublished: Instant = Instant.NONE,
    var timeUpdated: Instant = Instant.NONE,
    var visibility: ComTaskVisibility = ComTaskVisibility.NONE,
    var productId: ComTaskProductId = ComTaskProductId.NONE,
    val permissionsClient: MutableSet<ComTaskPermissionClient> = mutableSetOf()
) {
    fun deepCopy(): ComTask = copy(
        permissionsClient = permissionsClient.toMutableSet(),
    )
}
