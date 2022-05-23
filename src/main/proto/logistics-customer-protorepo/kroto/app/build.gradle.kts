import com.google.protobuf.gradle.protobuf
import com.google.protobuf.gradle.protoc

plugins {
    id("org.jetbrains.kotlin.jvm") version "1.5.31"
    id("com.google.protobuf") version "0.8.17"
    idea
    application
}

repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()
}

dependencies {
    // Align versions of all Kotlin components
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))

    // Use the Kotlin JDK 8 standard library.
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    // This dependency is used by the application.
    implementation("com.github.ajalt.clikt:clikt-jvm:3.2.0")
    implementation("com.google.protobuf:protobuf-java:3.17.3")
    implementation("commons-codec:commons-codec:1.15")
    implementation("org.apache.kafka:kafka-clients:2.8.0")
    implementation("com.google.protobuf:protobuf-java-util:3.18.0")
    implementation("ch.qos.logback:logback-classic:1.2.5")

    protobuf(files("../../protos"))

    testImplementation("org.jetbrains.kotlin:kotlin-test")

    // Use the Kotlin JUnit integration.
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = "16"
        freeCompilerArgs = listOf(
            "-Xinline-classes",
            "-Xopt-in=kotlin.RequiresOptIn",
        )
    }
}
java.sourceCompatibility = JavaVersion.VERSION_16
java.targetCompatibility = JavaVersion.VERSION_16

application {
    applicationName = "kroto"
    mainClass.set("com.deliveryhero.logistics.kroto.AppKt")
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:3.17.3"
    }
}
