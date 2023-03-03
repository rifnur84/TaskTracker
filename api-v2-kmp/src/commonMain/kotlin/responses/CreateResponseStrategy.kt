package ru.rifnur.tasktracker.api.v2.requests

import kotlinx.serialization.KSerializer
import ru.rifnur.tasktracker.api.v2.models.TaskCreateResponse
import ru.rifnur.tasktracker.api.v2.models.IResponse
import kotlin.reflect.KClass

object CreateResponseStrategy: IResponseStrategy {
    override val discriminator: String = "create"
    override val clazz: KClass<out IResponse> = TaskCreateResponse::class
    override val serializer: KSerializer<out IResponse> = TaskCreateResponse.serializer()
    override fun <T : IResponse> fillDiscriminator(req: T): T {
        require(req is TaskCreateResponse)
        @Suppress("UNCHECKED_CAST")
        return req.copy(responseType = discriminator) as T
    }
}
