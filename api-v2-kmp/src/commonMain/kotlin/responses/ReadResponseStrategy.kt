package ru.rifnur.tasktracker.api.v2.requests

import kotlinx.serialization.KSerializer
import ru.rifnur.tasktracker.api.v2.models.TaskReadResponse
import ru.rifnur.tasktracker.api.v2.models.IResponse
import kotlin.reflect.KClass

object ReadResponseStrategy: IResponseStrategy {
    override val discriminator: String = "read"
    override val clazz: KClass<out IResponse> = TaskReadResponse::class
    override val serializer: KSerializer<out IResponse> = TaskReadResponse.serializer()
    override fun <T : IResponse> fillDiscriminator(req: T): T {
        require(req is TaskReadResponse)
        @Suppress("UNCHECKED_CAST")
        return req.copy(responseType = discriminator) as T
    }
}
