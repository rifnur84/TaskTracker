package ru.rifnur.tasktracker.common.models

data class ComTask(
    var id: ComTaskId = ComTaskId.NONE,
    var title: String = "",
    var description: String = "",
    var datecreate: String= "",
    var ownerId: ComTaskUserId = ComTaskUserId.NONE,
    var visibility: ComTaskVisibility = ComTaskVisibility.NONE,
    var productId: ComTaskProductId = ComTaskProductId.NONE,
    val permissionsClient: MutableSet<ComTaskPermissionClient> = mutableSetOf()
)
