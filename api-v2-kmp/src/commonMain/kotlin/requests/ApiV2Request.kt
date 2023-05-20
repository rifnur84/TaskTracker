package ru.rifnur.tasktracker.api.v2.requests

import ru.rifnur.tasktracker.api.v2.TaskRequestSerializer
import ru.rifnur.tasktracker.api.v2.apiV2Mapper
import ru.rifnur.tasktracker.api.v2.models.IRequest

fun apiV2RequestSerialize(request: IRequest): String = apiV2Mapper.encodeToString(TaskRequestSerializer, request)

@Suppress("UNCHECKED_CAST")
fun <T : Any> apiV2RequestDeserialize(json: String): T = apiV2Mapper.decodeFromString(TaskRequestSerializer, json) as T
