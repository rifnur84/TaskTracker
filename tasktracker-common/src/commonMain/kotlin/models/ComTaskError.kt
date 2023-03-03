package ru.rifnur.tasktracker.common.models

data class ComTaskError(
    val code: String = "",
    val group: String = "",
    val field: String = "",
    val message: String = "",
    val exception: Throwable? = null,
)
