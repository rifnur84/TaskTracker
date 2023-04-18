package ru.rifnur.tasktracker.app.kafka

import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.clients.consumer.MockConsumer
import org.apache.kafka.clients.consumer.OffsetResetStrategy
import org.apache.kafka.clients.producer.MockProducer
import org.apache.kafka.common.TopicPartition
import org.apache.kafka.common.serialization.StringSerializer
import org.junit.Test
import ru.rifnur.tasktracker.api.v2.apiV2RequestSerialize
import ru.rifnur.tasktracker.api.v2.apiV2ResponseDeserialize
import ru.rifnur.tasktracker.api.v2.models.*
import java.util.*
import kotlin.test.assertEquals


class KafkaControllerTest {
    @Test
    fun runKafka() {
        val consumer = MockConsumer<String, String>(OffsetResetStrategy.EARLIEST)
        val producer = MockProducer<String, String>(true, StringSerializer(), StringSerializer())
        val config = AppKafkaConfig()
        val inputTopic = config.kafkaTopicInV2
        val outputTopic = config.kafkaTopicOutV2

        val app = AppKafkaConsumer(config, listOf(ConsumerStrategyV2()), consumer = consumer, producer = producer)
        consumer.schedulePollTask {
            consumer.rebalance(Collections.singletonList(TopicPartition(inputTopic, 0)))
            consumer.addRecord(
                ConsumerRecord(
                    inputTopic,
                    PARTITION,
                    0L,
                    "test-1",
                    apiV2RequestSerialize(
                        TaskCreateRequest(
                        requestId = "11111111-1111-1111-1111-111111111111",
                        task = TaskCreateObject(
                            title = "Задача 1. Решить уравнение",
                            description = "Требуется умножить 5 на 25",
                            visibility = TaskVisibility.OWNER_ONLY

                        ),
                        debug = TaskDebug(
                            mode = TaskRequestDebugMode.STUB,
                            stub = TaskRequestDebugStubs.SUCCESS
                        )
                    )
                    )
                )
            )
            app.stop()
        }

        val startOffsets: MutableMap<TopicPartition, Long> = mutableMapOf()
        val tp = TopicPartition(inputTopic, PARTITION)
        startOffsets[tp] = 0L
        consumer.updateBeginningOffsets(startOffsets)

        app.run()

        val message = producer.history().first()
        val result = apiV2ResponseDeserialize<TaskCreateResponse>(message.value())
        assertEquals(outputTopic, message.topic())
        assertEquals("11111111-1111-1111-1111-111111111111", result.requestId)
        assertEquals("Задача 1. Решить уравнение", result.task?.title)
    }

    companion object {
        const val PARTITION = 0
    }
}


