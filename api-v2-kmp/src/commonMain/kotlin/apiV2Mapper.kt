package ru.rifnur.tasktracker.api.v2

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.contextual
import ru.rifnur.tasktracker.api.v2.models.*

@OptIn(ExperimentalSerializationApi::class)
val apiV2Mapper = Json {
    classDiscriminator = "_"
    encodeDefaults = true
    ignoreUnknownKeys = true

    serializersModule = SerializersModule {
        polymorphicDefaultSerializer(IRequest::class) {
            @Suppress("UNCHECKED_CAST")
            when(it) {
                is TaskCreateRequest ->  RequestSerializer(TaskCreateRequest.serializer()) as SerializationStrategy<IRequest>
                is TaskReadRequest   ->  RequestSerializer(TaskReadRequest.serializer()) as SerializationStrategy<IRequest>
                is TaskUpdateRequest ->  RequestSerializer(TaskUpdateRequest.serializer()) as SerializationStrategy<IRequest>
                is TaskDeleteRequest ->  RequestSerializer(TaskDeleteRequest.serializer()) as SerializationStrategy<IRequest>
                is TaskSearchRequest ->  RequestSerializer(TaskSearchRequest.serializer()) as SerializationStrategy<IRequest>
                else -> null
            }
        }
        polymorphicDefaultSerializer(IResponse::class) {
            @Suppress("UNCHECKED_CAST")
            when(it) {
                is TaskCreateResponse ->  ResponseSerializer(TaskCreateResponse.serializer()) as SerializationStrategy<IResponse>
                is TaskReadResponse   ->  ResponseSerializer(TaskReadResponse.serializer()) as SerializationStrategy<IResponse>
                is TaskUpdateResponse ->  ResponseSerializer(TaskUpdateResponse.serializer()) as SerializationStrategy<IResponse>
                is TaskDeleteResponse ->  ResponseSerializer(TaskDeleteResponse.serializer()) as SerializationStrategy<IResponse>
                is TaskSearchResponse ->  ResponseSerializer(TaskSearchResponse.serializer()) as SerializationStrategy<IResponse>
                else -> null
            }
        }

        contextual(TaskRequestSerializer)
        contextual(TaskResponseSerializer)
    }
}

fun Json.encodeResponse(response: IResponse): String = encodeToString(TaskResponseSerializer, response)

fun apiV2ResponseSerialize(Response: IResponse): String = apiV2Mapper.encodeToString(TaskResponseSerializer, Response)

@Suppress("UNCHECKED_CAST")
fun <T : Any> apiV2ResponseDeserialize(json: String): T = apiV2Mapper.decodeFromString(TaskResponseSerializer, json) as T

fun apiV2RequestSerialize(request: IRequest): String = apiV2Mapper.encodeToString(TaskRequestSerializer, request)

@Suppress("UNCHECKED_CAST")
fun <T : Any> apiV2RequestDeserialize(json: String): T = apiV2Mapper.decodeFromString(TaskRequestSerializer, json) as T