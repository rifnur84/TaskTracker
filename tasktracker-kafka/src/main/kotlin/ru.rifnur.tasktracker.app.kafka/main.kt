package ru.rifnur.tasktracker.app.kafka

fun main() {
    val config = AppKafkaConfig()
    val consumer = AppKafkaConsumer(config, listOf(ConsumerStrategyV2()))
    consumer.run()
}
