package ru.rifnur.tasktracker

import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import ru.rifnur.tasktracker.app.module
import kotlin.test.Test
import kotlin.test.assertEquals

class ApplicationTest {
    @Test
    fun testReadStatus() = testApplication {application{module()}
        val response = client.post("/v2/task/read")
        assertEquals(HttpStatusCode.OK, response.status)
     }
    @Test
    fun testCreateStatus() = testApplication {application{module()}
        val response = client.post("/v2/task/create")
        assertEquals(HttpStatusCode.OK, response.status)
    }
    @Test
    fun testUpdateStatus() = testApplication {application{module()}
        val response = client.post("/v2/task/update")
        assertEquals(HttpStatusCode.OK, response.status)
    }
    @Test
    fun testDeleteStatus() = testApplication {application{module()}
        val response = client.post("/v2/task/delete")
        assertEquals(HttpStatusCode.OK, response.status)
    }
    @Test
    fun testSearchStatus()  = testApplication {application{module()}
        val response = client.post("/v2/task/search")
        assertEquals(HttpStatusCode.OK, response.status)
    }


}