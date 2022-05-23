package com.deliveryhero.logistics.kroto

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.requireObject
import com.github.ajalt.clikt.parameters.options.convert
import com.github.ajalt.clikt.parameters.options.default
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.required
import com.github.ajalt.clikt.parameters.types.file
import com.google.protobuf.GeneratedMessageV3
import com.google.protobuf.Parser
import com.google.protobuf.util.JsonFormat
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.common.serialization.ByteArrayDeserializer
import org.apache.kafka.common.serialization.StringDeserializer
import java.io.File
import java.time.Instant
import java.util.Properties
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
class DumpCommand : CliktCommand("dump records from a topic into a file as JSON lines") {
    private val outputFile by option(
        names = arrayOf("-o", "--output"),
        help = "Path of output file to dump the topic contents into"
    ).file().required()

    private val from by option(
        names = arrayOf("--from"),
        help = "Duration (eg: 1m, 1d) or ISO8601 string to start collecting stats from (default: beginning)"
    ).convert { it.parseAsTime() }

    private val until by option(
        names = arrayOf("--until"),
        help = "Duration (eg: 1m, 1d) or ISO8601 string until which to collect stats"
    ).convert { it.parseAsTime() }
        .default(Instant.now(), defaultForHelp = "now")

    private val recordKey by option(
        names = arrayOf("-k", "--recordKey"),
        help = "Dump only records having this key"
    )

    private val config by requireObject<Map<String, String>>()

    override fun run() {
        val props = Properties()
        props[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = config["bootstrap"]
        props[ConsumerConfig.CLIENT_ID_CONFIG] = "kroto"

        val topic = config["topic"]!!
        val parser = createParser(topic)
        val kafkaClient = KafkaClient(
            kafkaConsumer = KafkaConsumer(props, StringDeserializer(), ByteArrayDeserializer()),
            topic = topic,
        )
        KafkaDump(kafkaClient, from, until, parser, outputFile, recordKey).run()
    }
}

@OptIn(ExperimentalTime::class)
private class KafkaDump(
    private val kafkaClient: KafkaClient,
    private val from: Instant?,
    private val until: Instant,
    private val parser: Parser<out GeneratedMessageV3>,
    private val outputFile: File,
    private val recordKey: String?
) {

    fun run() {
        val printer = JsonFormat.printer().omittingInsignificantWhitespace()

        var count = 0
        var errorCount = 0
        outputFile.printWriter().use { writer ->
            kafkaClient.pollBetween(from, until).forEach {
                if (recordKey == null || recordKey == it.key()) {
                    try {
                        writer.println(printer.print(parser.parseFrom(it.value())))
                    } catch (e: Exception) {
                        println("Error parsing record: ${e.message}")
                        errorCount++
                    }
                    count++
                }
            }
        }
        System.err.println("Wrote $count record(s) to file ${outputFile.absolutePath}. Ignored $errorCount errors")
    }
}
