package ru.rifnur.tasktracker.api.v2.requests

import kotlinx.serialization.KSerializer
import ru.rifnur.tasktracker.api.v2.models.TaskCreateRequest
import ru.rifnur.tasktracker.api.v2.models.IRequest
import kotlin.reflect.KClass

object CreateRequestStrategy: IRequestStrategy {
    override val discriminator: String = "create"
    override val clazz: KClass<out IRequest> = TaskCreateRequest::class
    override val serializer: KSerializer<out IRequest> = TaskCreateRequest.serializer()
    override fun <T : IRequest> fillDiscriminator(req: T): T {
        require(req is TaskCreateRequest)
        @Suppress("UNCHECKED_CAST")
        return req.copy(requestType = discriminator) as T
    }
}
