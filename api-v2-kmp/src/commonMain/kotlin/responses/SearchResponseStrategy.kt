package ru.rifnur.tasktracker.api.v2.requests

import kotlinx.serialization.KSerializer
import ru.rifnur.tasktracker.api.v2.models.TaskSearchResponse
import ru.rifnur.tasktracker.api.v2.models.IResponse
import kotlin.reflect.KClass

object SearchResponseStrategy: IResponseStrategy {
    override val discriminator: String = "search"
    override val clazz: KClass<out IResponse> = TaskSearchResponse::class
    override val serializer: KSerializer<out IResponse> = TaskSearchResponse.serializer()
    override fun <T : IResponse> fillDiscriminator(req: T): T {
        require(req is TaskSearchResponse)
        @Suppress("UNCHECKED_CAST")
        return req.copy(responseType = discriminator) as T
    }
}
