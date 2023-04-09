package ru.rifnur.tasktracker.stubs

//class V2TaskStubApiTest {
//}
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.testing.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import ru.rifnur.tasktracker.api.v2.apiV2Mapper
import ru.rifnur.tasktracker.api.v2.models.*
import ru.rifnur.tasktracker.module
import kotlin.test.Test
import kotlin.test.assertEquals

class V2TaskStubApiTest {

    @Test
    fun create() = testApplication {
        application{module()}
        val response = client.post("/v2/task/create") {
            val requestObj = TaskCreateRequest(
                requestId = "12345",
                task = TaskCreateObject(
                    title = "Выполнить задачу 1",
                    description = "Драйвер для анализатора",
                    visibility = TaskVisibility.PUBLIC,
                ),
                debug = TaskDebug(
                    mode = TaskRequestDebugMode.STUB,
                    stub = TaskRequestDebugStubs.SUCCESS
                )
            )
            contentType(ContentType.Application.Json)
            val requestJson = apiV2Mapper.encodeToString(requestObj)
            setBody(requestJson)
        }
        val responseJson = response.bodyAsText()
        val responseObj = apiV2Mapper.decodeFromString<TaskCreateResponse>(responseJson)
        assertEquals(200, response.status.value)
        assertEquals("1", responseObj.task?.id)
    }

    @Test
    fun read() = testApplication {
        application{module()}
        val response = client.post("/v2/task/read") {
            val requestObj = TaskReadRequest(
                requestId = "12345",
                task = TaskReadObject("1"),
                debug = TaskDebug(
                    mode = TaskRequestDebugMode.STUB,
                    stub = TaskRequestDebugStubs.SUCCESS
                )
            )
            contentType(ContentType.Application.Json)
            val requestJson = apiV2Mapper.encodeToString(requestObj)
            setBody(requestJson)
        }
        val responseJson = response.bodyAsText()
        val responseObj = apiV2Mapper.decodeFromString<TaskReadResponse>(responseJson)
        assertEquals(200, response.status.value)
        assertEquals("1", responseObj.task?.id)
    }

    @Test
    fun update() = testApplication {
        application{module()}
        val response = client.post("/v2/task/update") {
            val requestObj = TaskUpdateRequest(
                requestId = "12345",
                task = TaskUpdateObject(
                    id = "1",
                    title = "Выполнить задачу 1",
                    description = "Драйвер для анализатора",
//                    adType = DealSide.DEMAND,
                    visibility = TaskVisibility.PUBLIC,
                ),
                debug = TaskDebug(
                    mode = TaskRequestDebugMode.STUB,
                    stub = TaskRequestDebugStubs.SUCCESS
                )
            )
            contentType(ContentType.Application.Json)
            val requestJson = apiV2Mapper.encodeToString(requestObj)
            setBody(requestJson)
        }
        val responseJson = response.bodyAsText()
        val responseObj = apiV2Mapper.decodeFromString<TaskUpdateResponse>(responseJson)
        assertEquals(200, response.status.value)
        assertEquals("1", responseObj.task?.id)
    }

    @Test
    fun delete() = testApplication {
        application{module()}
        val response = client.post("/v2/task/delete") {
            val requestObj = TaskDeleteRequest(
                requestId = "12345",
                task = TaskDeleteObject(
                    id = "1",
                    lock = "123"
                ),
                debug = TaskDebug(
                    mode = TaskRequestDebugMode.STUB,
                    stub = TaskRequestDebugStubs.SUCCESS
                )
            )
            contentType(ContentType.Application.Json)
            val requestJson = apiV2Mapper.encodeToString(requestObj)
            setBody(requestJson)
        }
        val responseJson = response.bodyAsText()
        val responseObj = apiV2Mapper.decodeFromString<TaskDeleteResponse>(responseJson)
        assertEquals(200, response.status.value)
        assertEquals("1", responseObj.task?.id)
    }

    @Test
    fun search() = testApplication {
        application{module()}
        val response = client.post("/v2/task/search") {
            val requestObj = TaskSearchRequest(
                requestId = "12345",
                taskFilter = TaskSearchFilter(),
                debug = TaskDebug(
                    mode = TaskRequestDebugMode.STUB,
                    stub = TaskRequestDebugStubs.SUCCESS
                )
            )
            contentType(ContentType.Application.Json)
            val requestJson = apiV2Mapper.encodeToString(requestObj)
            setBody(requestJson)
        }
        val responseJson = response.bodyAsText()
        val responseObj = apiV2Mapper.decodeFromString<TaskSearchResponse>(responseJson)
        assertEquals(200, response.status.value)
        assertEquals("d-666-01", responseObj.tasks?.first()?.id)
    }

}
