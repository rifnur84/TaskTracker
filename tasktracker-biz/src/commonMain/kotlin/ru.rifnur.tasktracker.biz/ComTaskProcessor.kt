package ru.rifnur.tasktracker.biz

import ru.rifnur.tasktracker.common.ComTaskContext
import ru.rifnur.tasktracker.stubs.ComTaskStub
class ComTaskProcessor {
    suspend fun exec(ctx: ComTaskContext) {
        ctx.taskResponse = ComTaskStub.get()
    }
}

