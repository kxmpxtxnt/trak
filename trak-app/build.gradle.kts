@file:OptIn(ExperimentalWasmDsl::class)

import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.KotlinVersion

plugins {
    kotlin("multiplatform") version "2.2.21"
    kotlin("plugin.serialization") version "2.2.21"

    id("org.jetbrains.compose") version "1.9.3"
    id("com.android.application") version "8.12.3"
    id("org.jetbrains.kotlin.plugin.compose") version "2.2.21"
}

repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
}

android {
    namespace = "${project.group}.trak"
    compileSdk = 36

    defaultConfig {
        applicationId = "fyi.pauli.trak"
        minSdk = 24
        targetSdk = 36
        versionCode = rootProject.version.toString().replace(".", "").toInt()
        versionName = rootProject.version.toString()
    }

    packaging {
        resources.excludes += "/META-INF/{AL2.0,LGPL2.1}"
    }

    buildTypes {
        getByName("release").isMinifyEnabled = false
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

kotlin {
    androidTarget()

    compilerOptions {
        freeCompilerArgs.add("-Xexpect-actual-classes")

        apiVersion = KotlinVersion.KOTLIN_2_2
        languageVersion = KotlinVersion.KOTLIN_2_2
    }

    applyDefaultHierarchyTemplate()

    sourceSets {
        all {
            languageSettings.optIn("kotlin.uuid.ExperimentalUuidApi")
            languageSettings.optIn("kotlinx.serialization.ExperimentalSerializationApi")
        }

        androidMain.dependencies {
            implementation(compose.preview)

            implementation("androidx.room:room-ktx:2.8.3")
            implementation("androidx.core:core-ktx:1.17.0")
            implementation("androidx.room:room-runtime:2.8.3")
            implementation("androidx.appcompat:appcompat:1.7.1")
            implementation("androidx.activity:activity-compose:1.11.0")
            implementation("androidx.compose.material3:material3:1.4.0")
            implementation("androidx.navigation:navigation-compose:2.9.6")
            implementation("androidx.datastore:datastore-preferences:1.1.7")
            implementation("androidx.constraintlayout:constraintlayout:2.2.1")

            implementation("org.jetbrains.androidx.lifecycle:lifecycle-viewmodel:2.9.6")
            implementation("org.jetbrains.androidx.lifecycle:lifecycle-runtime-compose:2.9.6")

            implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.7.1")
            implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:1.9.0")
            implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.9.0")
            implementation("org.jetbrains.kotlinx:kotlinx-serialization-protobuf:1.9.0")

            implementation("com.google.android.material:material:1.13.0")

            implementation("io.github.oshai:kotlin-logging:7.0.13")

            implementation("io.insert-koin:koin-android:4.1.1")
            implementation("io.insert-koin:koin-androidx-compose-navigation:4.1.1")

            implementation("cafe.adriel.voyager:voyager-navigator:1.1.0-beta03")
            implementation("cafe.adriel.voyager:voyager-screenmodel:1.1.0-beta03")
        }
    }
}
