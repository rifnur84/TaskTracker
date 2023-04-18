package ru.rifnur.tasktracker.app.kafka

import ru.rifnur.tasktracker.api.v2.apiV2RequestDeserialize
import ru.rifnur.tasktracker.api.v2.apiV2ResponseSerialize
import ru.rifnur.tasktracker.api.v2.models.IRequest
import ru.rifnur.tasktracker.api.v2.models.IResponse
import ru.rifnur.tasktracker.common.ComTaskContext
import ru.rifnur.tasktracker.mappers.v2.fromTransport
import ru.rifnur.tasktracker.mappers.v2.toTransportTask

class ConsumerStrategyV2 : ConsumerStrategy {
    override fun topics(config: AppKafkaConfig): InputOutputTopics {
        return InputOutputTopics(config.kafkaTopicInV2, config.kafkaTopicOutV2)
    }

    override fun serialize(source: ComTaskContext): String {
        val response: IResponse = source.toTransportTask()
        return apiV2ResponseSerialize(response)
    }

    override fun deserialize(value: String, target: ComTaskContext) {
        val request: IRequest = apiV2RequestDeserialize(value)
        target.fromTransport(request)
    }
}