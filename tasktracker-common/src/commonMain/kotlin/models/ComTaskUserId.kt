package ru.rifnur.tasktracker.common.models

import kotlin.jvm.JvmInline

@JvmInline
value class ComTaskUserId(private val id: String) {
    fun asString() = id

    companion object {
        val NONE = ComTaskUserId("")
    }
}
