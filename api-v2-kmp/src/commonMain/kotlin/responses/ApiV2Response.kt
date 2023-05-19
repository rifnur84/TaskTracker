package ru.rifnur.tasktracker.api.v2.responses

import ru.rifnur.tasktracker.api.v2.TaskResponseSerializer
import ru.rifnur.tasktracker.api.v2.apiV2Mapper
import ru.rifnur.tasktracker.api.v2.models.IResponse

fun apiV2ResponseSerialize(Response: IResponse): String = apiV2Mapper.encodeToString(TaskResponseSerializer, Response)

@Suppress("UNCHECKED_CAST")
fun <T : Any> apiV2ResponseDeserialize(json: String): T = apiV2Mapper.decodeFromString(TaskResponseSerializer, json) as T
