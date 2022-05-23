package com.deliveryhero.logistics.kroto

import java.time.Instant
import kotlin.time.Duration
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
fun String.parseAsTime(): Instant = runCatching { Duration.parse(this) }
    .map { Instant.now().minusMillis(it.inWholeMilliseconds) }
    .recoverCatching { Instant.parse(this) }
    .getOrElse { throw IllegalArgumentException("Could not parse input as Duration or Instant") }
