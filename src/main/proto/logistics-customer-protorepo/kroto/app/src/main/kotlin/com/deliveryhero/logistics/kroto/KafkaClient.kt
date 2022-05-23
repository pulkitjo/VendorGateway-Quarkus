package com.deliveryhero.logistics.kroto

import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.common.TopicPartition
import java.time.Duration
import java.time.Instant
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
class KafkaClient(
    private val kafkaConsumer: KafkaConsumer<String, ByteArray>,
    private val topic: String,
) {

    private fun List<TopicPartition>.offsetsFor(epochMillis: Long) =
        kafkaConsumer.offsetsForTimes(associateWith { epochMillis })

    private fun retrievePartitions() = kafkaConsumer.partitionsFor(topic, Duration.ofSeconds(5)).map {
        TopicPartition(it.topic(), it.partition())
    }

    fun pollFrom(from: Instant): Sequence<ConsumerRecord<String, ByteArray>> = sequence {
        val partitions = retrievePartitions()
        kafkaConsumer.assign(partitions)

        partitions.offsetsFor(from.toEpochMilli())
            .map { (partition, offsetAndTimestamp) ->
                if (offsetAndTimestamp != null) {
                    System.err.println(
                        "Partition ${partition.partition()}: Using offset: ${offsetAndTimestamp.offset()} since $from"
                    )
                    kafkaConsumer.seek(partition, offsetAndTimestamp.offset())
                } else {
                    kafkaConsumer.seekToEnd(listOf(partition))
                }
            }
        while (true) {
            kafkaConsumer.poll(Duration.ofDays(1)).forEach { yield(it) }
        }
    }

    fun pollBetween(from: Instant?, until: Instant): Sequence<ConsumerRecord<String, ByteArray>> = sequence {
        val partitions = retrievePartitions()

        val fromOffsetsMap = if (from == null) {
            kafkaConsumer.beginningOffsets(partitions)
                .mapNotNull { (tp, ot) -> ot?.let { tp to it } }
                .toMap()
        } else {
            partitions.offsetsFor(from.toEpochMilli())
                .mapNotNull { (tp, ot) -> ot?.let { tp to it.offset() } }
                .toMap()
        }

        val partitionsWithData = fromOffsetsMap.keys.toList()
        val untilOffsetsMap = kafkaConsumer.endOffsets(partitionsWithData)
            .mapNotNull { (tp, offset) -> offset?.let { tp.partition() to (it - 1) } }
            .toMap() +
            kafkaConsumer.offsetsForTimes(partitionsWithData.associateWith { until.toEpochMilli() })
                .mapNotNull { (tp, ot) -> ot?.let { tp.partition() to it.offset() } }
                .toMap()

        if (fromOffsetsMap.isNotEmpty()) {
            kafkaConsumer.assign(fromOffsetsMap.keys)
            fromOffsetsMap.forEach { (tp, offset) -> kafkaConsumer.seek(tp, offset) }
            val unprocessedPartitions = fromOffsetsMap.keys.map(TopicPartition::partition).toMutableSet()

            while (true) {
                kafkaConsumer.poll(Duration.ofSeconds(1)).forEach {
                    val partition = it.partition()
                    if (unprocessedPartitions.contains(partition)) {
                        if (it.offset() <= untilOffsetsMap[partition]!! && it.timestamp() < until.toEpochMilli()) {
                            yield(it)
                        }

                        if (it.offset() >= untilOffsetsMap[partition]!!) {
                            unprocessedPartitions.remove(partition)
                        }
                    }
                }
                if (unprocessedPartitions.isEmpty()) {
                    break
                }
            }
        }
    }

}
