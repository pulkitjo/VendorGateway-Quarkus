package com.deliveryhero.logistics.kroto

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.context
import com.github.ajalt.clikt.core.findOrSetObject
import com.github.ajalt.clikt.core.subcommands
import com.github.ajalt.clikt.output.CliktHelpFormatter
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.required
import com.github.ajalt.clikt.parameters.types.choice
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
fun main(args: Array<String>) {
    Cli().subcommands(TailCommand(), StatsCommand(), DumpCommand()).main(args)
}

class Cli : CliktCommand(name = "kroto") {
    init {
        context { helpFormatter = CliktHelpFormatter(showDefaultValues = true) }
    }

    private val bootstrap: String? by option(names = arrayOf("-b", "--bootstrap"), help = "Bootstrap Brokers")
    private val cluster by option(names = arrayOf("-c", "--cluster")).choice(
        "staging" to "staging.staging-eu",
        "production-eu" to "production.production-eu",
        "load-tests" to "load-tests.load-tests-eu",
    )
    private val topic: String by option(names = arrayOf("-t", "--topic"), help = "Kafka topic name").required()

    private val config by findOrSetObject { mutableMapOf<String, String>() }
    override fun run() {
        config["topic"] = topic
        buildBootstrapServers(bootstrap, cluster).fold(
            onSuccess = { config["bootstrap"] = it },
            onFailure = { throw it }
        )
    }
}

private fun buildBootstrapServers(bootstrap: String?, cluster: String?): Result<String> = when {
    bootstrap != null -> Result.success(bootstrap)
    cluster != null -> Result.success("broker01.log-customer-$cluster.tf:9092")
    else -> Result.failure(RuntimeException("One of --bootstrap or --cluster needs to be passed in"))
}
