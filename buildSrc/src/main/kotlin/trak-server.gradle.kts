import io.ktor.plugin.features.*
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    kotlin("jvm")
    id("io.ktor.plugin")
    id("app.cash.sqldelight")
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_21
        optIn = listOf("kotlin.uuid.ExperimentalUuidApi")
    }
}

dependencies {
    implementation("io.ktor:ktor-server-cio")
    implementation("io.ktor:ktor-server-sse")
    implementation("io.ktor:ktor-server-core")
    implementation("io.ktor:ktor-server-sessions")
    implementation("io.ktor:ktor-server-resources")
    implementation("io.ktor:ktor-server-content-negotiation")

    implementation("io.ktor:ktor-client-auth")
    implementation("io.ktor:ktor-client-resources")
    implementation("io.ktor:ktor-client-content-negotiation")

    implementation("io.ktor:ktor-serialization-kotlinx-json")

    runtimeOnly("ch.qos.logback:logback-classic:1.5.18")
    implementation("io.github.oshai:kotlin-logging-jvm:7.0.3")
}

sqldelight {
    databases {
        create("trak") {
            packageName = project.group.toString()
            dialect("app.cash.sqldelight:postgresql-dialect:2.1.0")
        }
    }
}

application {
    mainClass = "${project.group}.trak.TrakKt"
}

ktor {
    development = properties["development"] == true

    fatJar {
        archiveFileName = "trak-server-${project.version}-fat.jar"
    }

    docker {
        jreVersion = JavaVersion.VERSION_21

        localImageName = "trak-server"
        imageTag = project.version.toString()

        portMappings = listOf(
            DockerPortMapping(
                80,
                8080,
                DockerPortMappingProtocol.TCP
            )
        )
    }
}