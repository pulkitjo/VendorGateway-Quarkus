package com.deliveryhero.logistics.kroto

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.requireObject
import com.github.ajalt.clikt.parameters.options.convert
import com.github.ajalt.clikt.parameters.options.default
import com.github.ajalt.clikt.parameters.options.option
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.common.serialization.ByteArrayDeserializer
import org.apache.kafka.common.serialization.StringDeserializer
import java.time.Instant
import java.util.Properties
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
class StatsCommand : CliktCommand("print statistics about the topic") {
    private val from by option(
        names = arrayOf("--from"),
        help = "Duration (eg: 1m, 1d) or ISO8601 string to start collecting stats from (default: beginning)"
    ).convert { it.parseAsTime() }

    private val until by option(
        names = arrayOf("--until"),
        help = "Duration (eg: 1m, 1d) or ISO8601 string until which to collect stats"
    ).convert { it.parseAsTime() }
        .default(Instant.now(), defaultForHelp = "now")

    private val config by requireObject<Map<String, String>>()

    override fun run() {
        val props = Properties()
        props[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = config["bootstrap"]
        props[ConsumerConfig.CLIENT_ID_CONFIG] = "kroto"

        val kafkaClient = KafkaClient(
            kafkaConsumer = KafkaConsumer(props, StringDeserializer(), ByteArrayDeserializer()),
            topic = config["topic"]!!,
        )
        KafkaStats(kafkaClient, from, until).run()
    }
}

@OptIn(ExperimentalTime::class)
private class KafkaStats(private val kafkaClient: KafkaClient, private val from: Instant?, private val until: Instant) {

    fun run() {
        val partitionCounts = mutableMapOf<Int, Int>()
        val uniqueVendors = mutableSetOf<String>()

        kafkaClient.pollBetween(from, until).forEach {
            partitionCounts.compute(it.partition()) { _, count -> (count ?: 0) + 1 }
            uniqueVendors.add(it.key())
        }

        println("Unique vendor count: ${uniqueVendors.size}")
        val total = partitionCounts.values.sum()
        println("Total records: $total")
        if (total > 0) {
            println("\nRecord counts by partition:")
            partitionCounts.forEach { (partition, count) ->
                println("Partition $partition: $count")
            }
        }
    }
}
