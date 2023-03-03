package ru.rifnur.tasktracker.api.v2.requests

import kotlinx.serialization.KSerializer
import ru.rifnur.tasktracker.api.v2.models.TaskReadRequest
import ru.rifnur.tasktracker.api.v2.models.IRequest
import kotlin.reflect.KClass

object ReadRequestStrategy: IRequestStrategy {
    override val discriminator: String = "read"
    override val clazz: KClass<out IRequest> = TaskReadRequest::class
    override val serializer: KSerializer<out IRequest> = TaskReadRequest.serializer()
    override fun <T : IRequest> fillDiscriminator(req: T): T {
        require(req is TaskReadRequest)
        @Suppress("UNCHECKED_CAST")
        return req.copy(requestType = discriminator) as T
    }
}
