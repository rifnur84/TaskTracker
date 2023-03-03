package ru.rifnur.tasktracker.api.v2

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import ru.rifnur.tasktracker.api.v2.models.*
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals

class ResponseSerializationTest {
    private val response: IResponse = TaskCreateResponse(
        responseType = "create",
        requestId = "123",
        task = TaskResponseObject(
            title = "task title",
            description = "task description",
            visibility = TaskVisibility.PUBLIC,
        )
    )

    @Test
    fun serialize() {
//        val json = apiV2Mapper.encodeToString(TaskRequestSerializer1, request)
//        val json = apiV2Mapper.encodeToString(RequestSerializers.create, request)
        val json = apiV2Mapper.encodeToString(response)

        println(json)

        assertContains(json, Regex("\"title\":\\s*\"task title\""))
        assertContains(json, Regex("\"responseType\":\\s*\"create\""))
    }

    @Test
    fun deserialize() {
        val json = apiV2Mapper.encodeToString(response)
//        val json = apiV2Mapper.encodeToString(TaskRequestSerializer1, request)
//        val json = apiV2Mapper.encodeToString(RequestSerializers.create, request)
//        val obj = apiV2Mapper.decodeFromString(TaskRequestSerializer, json) as TaskCreateRequest
        val obj = apiV2Mapper.decodeFromString(json) as TaskCreateResponse

        assertEquals(response, obj)
    }
}
