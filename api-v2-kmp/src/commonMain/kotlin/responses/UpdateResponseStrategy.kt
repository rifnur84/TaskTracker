package ru.rifnur.tasktracker.api.v2.requests

import kotlinx.serialization.KSerializer
import ru.rifnur.tasktracker.api.v2.models.TaskUpdateResponse
import ru.rifnur.tasktracker.api.v2.models.IResponse
import kotlin.reflect.KClass

object UpdateResponseStrategy: IResponseStrategy {
    override val discriminator: String = "update"
    override val clazz: KClass<out IResponse> = TaskUpdateResponse::class
    override val serializer: KSerializer<out IResponse> = TaskUpdateResponse.serializer()
    override fun <T : IResponse> fillDiscriminator(req: T): T {
        require(req is TaskUpdateResponse)
        @Suppress("UNCHECKED_CAST")
        return req.copy(responseType = discriminator) as T
    }
}
