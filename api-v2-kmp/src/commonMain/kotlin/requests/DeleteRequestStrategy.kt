package ru.rifnur.tasktracker.api.v2.requests

import kotlinx.serialization.KSerializer
import ru.rifnur.tasktracker.api.v2.models.TaskDeleteRequest
import ru.rifnur.tasktracker.api.v2.models.IRequest
import kotlin.reflect.KClass

object DeleteRequestStrategy: IRequestStrategy {
    override val discriminator: String = "delete"
    override val clazz: KClass<out IRequest> = TaskDeleteRequest::class
    override val serializer: KSerializer<out IRequest> = TaskDeleteRequest.serializer()
    override fun <T : IRequest> fillDiscriminator(req: T): T {
        require(req is TaskDeleteRequest)
        @Suppress("UNCHECKED_CAST")
        return req.copy(requestType = discriminator) as T
    }
}
