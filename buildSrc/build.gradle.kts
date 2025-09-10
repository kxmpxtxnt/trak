plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    fun plugin(id: String, version: String) = "$id:$id.gradle.plugin:$version"

    val kotlinVersion = "2.2.20"
    val androidVersion = "8.11.1"

    implementation(kotlin("gradle-plugin", kotlinVersion))

    compileOnly(plugin("org.jetbrains.kotlin.plugin.serialization", embeddedKotlinVersion))
    runtimeOnly(plugin("org.jetbrains.kotlin.plugin.serialization", kotlinVersion))

    implementation(plugin("com.android.library", androidVersion))
    implementation(plugin("com.android.application", androidVersion))

    implementation(plugin("com.android.kotlin.multiplatform.library", androidVersion))

    implementation(plugin("com.google.devtools.ksp", "2.1.0-1.0.29"))

    implementation(plugin("org.jetbrains.compose", "1.8.2"))
    implementation(plugin("org.jetbrains.kotlin.plugin.compose", kotlinVersion))

    implementation(plugin("app.cash.sqldelight", "2.1.0"))

    implementation(plugin("io.ktor.plugin", "3.2.3"))
}