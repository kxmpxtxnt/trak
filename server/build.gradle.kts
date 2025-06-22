import io.ktor.plugin.features.*

plugins {
    alias(ktorio.plugins.ktor)
    alias(jetbrains.plugins.kotlin.jvm)
}

dependencies {
    implementation(ktorio.bundles.ktor)
}

application {
    mainClass = "fyi.pauli.trak.server.TrakKt"
}

ktor {
    fatJar.archiveFileName = "trak-server-all.jar"

    docker {
        jreVersion = JavaVersion.VERSION_21
        localImageName = "trak-server"
        imageTag = rootProject.version.toString()

        portMappings = listOf(
            DockerPortMapping(8765, 8765, DockerPortMappingProtocol.TCP)
        )
    }
}