package ru.rifnur.tasktracker.api.v2.requests

import ru.rifnur.tasktracker.api.v2.IApiStrategy
import ru.rifnur.tasktracker.api.v2.models.IRequest

sealed interface IRequestStrategy: IApiStrategy<IRequest> {
    companion object {
        private val members: List<IRequestStrategy> = listOf(
            CreateRequestStrategy,
            ReadRequestStrategy,
            UpdateRequestStrategy,
            DeleteRequestStrategy,
            SearchRequestStrategy,
        )
        val membersByDiscriminator = members.associateBy { it.discriminator }
        val membersByClazz = members.associateBy { it.clazz }
    }
}
