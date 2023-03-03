package ru.rifnur.tasktracker.api.v2.requests

import kotlinx.serialization.KSerializer
import ru.rifnur.tasktracker.api.v2.models.TaskDeleteResponse
import ru.rifnur.tasktracker.api.v2.models.IResponse
import kotlin.reflect.KClass

object DeleteResponseStrategy: IResponseStrategy {
    override val discriminator: String = "delete"
    override val clazz: KClass<out IResponse> = TaskDeleteResponse::class
    override val serializer: KSerializer<out IResponse> = TaskDeleteResponse.serializer()
    override fun <T : IResponse> fillDiscriminator(req: T): T {
        require(req is TaskDeleteResponse)
        @Suppress("UNCHECKED_CAST")
        return req.copy(responseType = discriminator) as T
    }
}
