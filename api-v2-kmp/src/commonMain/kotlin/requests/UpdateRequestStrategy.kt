package ru.rifnur.tasktracker.api.v2.requests

import kotlinx.serialization.KSerializer
import ru.rifnur.tasktracker.api.v2.models.TaskUpdateRequest
import ru.rifnur.tasktracker.api.v2.models.IRequest
import kotlin.reflect.KClass

object UpdateRequestStrategy: IRequestStrategy {
    override val discriminator: String = "update"
    override val clazz: KClass<out IRequest> = TaskUpdateRequest::class
    override val serializer: KSerializer<out IRequest> = TaskUpdateRequest.serializer()
    override fun <T : IRequest> fillDiscriminator(req: T): T {
        require(req is TaskUpdateRequest)
        @Suppress("UNCHECKED_CAST")
        return req.copy(requestType = discriminator) as T
    }
}
