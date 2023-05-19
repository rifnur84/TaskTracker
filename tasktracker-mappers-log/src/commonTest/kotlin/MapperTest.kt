
import ru.rifnur.tasktracker.api.logs.mapper.toLog
import ru.rifnur.tasktracker.common.ComTaskContext
import ru.rifnur.tasktracker.common.models.*
import kotlin.test.Test
import kotlin.test.assertEquals

class MapperTest {

    @Test
    fun fromContext() {
        val context = ComTaskContext(
            requestId = ComTaskRequestId("1234"),
            command = ComTaskCommand.CREATE,
            taskResponse = ComTask(
                title = "title",
                description = "desc",
                visibility = ComTaskVisibility.VISIBLE_PUBLIC,
            ),
            errors = mutableListOf(
                ComTaskError(
                    code = "err",
                    group = "request",
                    field = "title",
                    message = "wrong title",
                )
            ),
            state = ComTaskState.RUNNING,
        )

        val log = context.toLog("test-id")

        assertEquals("test-id", log.logId)
        assertEquals("tasktracker", log.source)
        assertEquals("1234", log.task?.requestId)
        assertEquals("VISIBLE_PUBLIC", log.task?.responseTask?.visibility)
        val error = log.errors?.firstOrNull()
        assertEquals("wrong title", error?.message)
        assertEquals("ERROR", error?.level)
    }
}
