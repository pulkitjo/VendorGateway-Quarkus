package com.deliveryhero.logistics.kroto

import com.deliveryhero.logistics.customer.datafridge.v1beta1.DeliveryAreaEvent
import com.deliveryhero.logistics.customer.datafridge.v1beta1.Vendor
import com.deliveryhero.logistics.customer.datafridge.v1beta1.VendorDeliveryArea
import com.deliveryhero.logistics.customer.datafridge.v1beta1.VendorSchedule
import com.deliveryhero.logistics.customer.datafridge.v1beta1.VendorUnavailabilityEvent
import com.deliveryhero.logistics.customer.laas.v1beta1.SummarizedDeliveryAreaEvent
import com.deliveryhero.logistics.customer.laas.v1beta1.SummarizedVendor
import com.deliveryhero.logistics.customer.laas.v1beta1.SummarizedVendorGeoData
import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.requireObject
import com.github.ajalt.clikt.parameters.options.convert
import com.github.ajalt.clikt.parameters.options.default
import com.github.ajalt.clikt.parameters.options.flag
import com.github.ajalt.clikt.parameters.options.option
import com.google.protobuf.GeneratedMessageV3
import com.google.protobuf.Parser
import com.google.protobuf.util.JsonFormat
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.common.serialization.ByteArrayDeserializer
import org.apache.kafka.common.serialization.StringDeserializer
import java.time.Instant
import java.time.temporal.ChronoUnit
import java.util.Properties
import kotlin.system.exitProcess
import kotlin.time.ExperimentalTime

private val ParserMap: Map<String, Parser<out GeneratedMessageV3>> = mapOf(
    "laas.raw-vendor" to Vendor.parser(),
    "laas.vendor-stream" to Vendor.parser(),
    "laas.summarized-vendor" to SummarizedVendor.parser(),
    "laas.raw-vendor-delivery-area" to VendorDeliveryArea.parser(),
    "laas.vendor-delivery-area-stream" to VendorDeliveryArea.parser(),
    "laas.summarized-vendor-geo-data" to SummarizedVendorGeoData.parser(),
    "laas.raw-delivery-area-event" to DeliveryAreaEvent.parser(),
    "laas.delivery-area-event-stream" to DeliveryAreaEvent.parser(),
    "laas.summarized-delivery-area-event" to SummarizedDeliveryAreaEvent.parser(),
    "laas.raw-vendor-schedule" to VendorSchedule.parser(),
    "laas.vendor-schedule-stream" to VendorSchedule.parser(),
    "laas.raw-vendor-availability" to VendorUnavailabilityEvent.parser(),
    "laas.vendor-availability-stream" to VendorUnavailabilityEvent.parser(),
)

fun createParser(topic: String): Parser<out GeneratedMessageV3> {
    val parser = ParserMap[topic.split(".").take(2).joinToString(".")]
    return if (parser == null) {
        System.err.println("Parser mapping not defined for topic '$topic'")
        exitProcess(1)
    } else parser
}

@OptIn(ExperimentalTime::class)
class TailCommand : CliktCommand("tail the topic") {
    private val from by option(
        names = arrayOf("--from"),
        help = "Duration (eg: 1m, 1d) or ISO8601 string to start tailing from from"
    )
        .convert { it.parseAsTime() }
        .default(Instant.now().minus(1, ChronoUnit.MINUTES), "1m")

    private val pretty by option(names = arrayOf("--pretty"), help = "Pretty print the JSON output").flag()

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
        KafkaTailer(
            kafkaClient = kafkaClient,
            from = from,
            parser = parser,
            prettyPrint = pretty,
        ).run()
    }
}

@OptIn(ExperimentalTime::class)
private class KafkaTailer(
    private val kafkaClient: KafkaClient,
    private val from: Instant,
    private val parser: Parser<out GeneratedMessageV3>,
    private val prettyPrint: Boolean
) {

    fun run() {
        val printer = JsonFormat.printer().let {
            if (prettyPrint) it else it.omittingInsignificantWhitespace()
        }

        kafkaClient.pollFrom(from).forEach {
            println(
                "${Instant.ofEpochMilli(it.timestamp())} Key: ${it.key()}, Payload: ${
                printer.print(parser.parseFrom(it.value()))
                }"
            )
        }
    }
}
