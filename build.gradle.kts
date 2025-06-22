plugins {
    alias(google.plugins.ksp) apply false
    alias(ktorio.plugins.ktor) apply false
    alias(agp.plugins.library) apply false
    alias(agp.plugins.appplication) apply false
    alias(jetbrains.plugins.kotlin.jvm) apply false
    alias(jetbrains.plugins.kotlin.compose) apply false
    alias(jetbrains.plugins.kotlin.multiplatform) apply false
    alias(jetbrains.plugins.compose.multiplatform) apply false
}

allprojects {
    version = "1.0.0"
    group = "fyi.pauli"

    repositories {
        google()
        mavenCentral()
    }
}