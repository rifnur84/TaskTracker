package ru.rifnur.tasktracker.api.v2.requests

import kotlinx.serialization.KSerializer
import ru.rifnur.tasktracker.api.v2.models.TaskSearchRequest
import ru.rifnur.tasktracker.api.v2.models.IRequest
import kotlin.reflect.KClass

object SearchRequestStrategy: IRequestStrategy {
    override val discriminator: String = "search"
    override val clazz: KClass<out IRequest> = TaskSearchRequest::class
    override val serializer: KSerializer<out IRequest> = TaskSearchRequest.serializer()
    override fun <T : IRequest> fillDiscriminator(req: T): T {
        require(req is TaskSearchRequest)
        @Suppress("UNCHECKED_CAST")
        return req.copy(requestType = discriminator) as T
    }
}
