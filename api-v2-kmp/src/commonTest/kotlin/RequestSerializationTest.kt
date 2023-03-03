package ru.rifnur.tasktracker.api.v2

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import ru.rifnur.tasktracker.api.v2.models.*
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals

class RequestSerializationTest {
    private val request: IRequest = TaskCreateRequest(
        requestType = "create",
        requestId = "123",
        debug = TaskDebug(
            mode = TaskRequestDebugMode.STUB,
            stub = TaskRequestDebugStubs.BAD_TITLE
        ),
        task = TaskCreateObject(
            title = "task title",
            description = "task description",
            visibility = TaskVisibility.PUBLIC,
        )
    )

    @Test
    fun serialize() {
//        val json = apiV2Mapper.encodeToString(TaskRequestSerializer1, request)
//        val json = apiV2Mapper.encodeToString(RequestSerializers.create, request)
        val json = apiV2Mapper.encodeToString(request)

        println(json)

        assertContains(json, Regex("\"title\":\\s*\"task title\""))
        assertContains(json, Regex("\"mode\":\\s*\"stub\""))
        assertContains(json, Regex("\"stub\":\\s*\"badTitle\""))
        assertContains(json, Regex("\"requestType\":\\s*\"create\""))
    }

    @Test
    fun deserialize() {
        val json = apiV2Mapper.encodeToString(request)
//        val json = apiV2Mapper.encodeToString(TaskRequestSerializer1, request)
//        val json = apiV2Mapper.encodeToString(RequestSerializers.create, request)
//        val obj = apiV2Mapper.decodeFromString(TaskRequestSerializer, json) as TaskCreateRequest
        val obj = apiV2Mapper.decodeFromString(json) as TaskCreateRequest

        assertEquals(request, obj)
    }
}
