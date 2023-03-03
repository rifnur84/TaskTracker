package ru.rifnur.tasktracker.common.models

data class ComTaskFilter(
    var searchString: String = "",
    var ownerId: ComTaskUserId = ComTaskUserId.NONE,
)
