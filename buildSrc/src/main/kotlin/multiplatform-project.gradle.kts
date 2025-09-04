@file:OptIn(ExperimentalWasmDsl::class)

import com.android.build.api.dsl.androidLibrary
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinVersion
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

plugins {
    kotlin("multiplatform")
    id("com.android.kotlin.multiplatform.library")
}

kotlin {
    androidLibrary {
        namespace = "${project.group}.trak"
        compileSdk = 36
        minSdk = 24

        withJava()

        compilerOptions {
            jvmTarget = JvmTarget.JVM_1_8
        }
    }

    explicitApi()

    compilerOptions {
        freeCompilerArgs.add("-Xexpect-actual-classes")

        apiVersion = KotlinVersion.KOTLIN_2_2
        languageVersion = KotlinVersion.KOTLIN_2_2
    }

    jvm()

    linuxX64()
    linuxArm64()

    mingwX64()

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "trak"
            isStatic = true
        }
    }

    macosX64()
    macosArm64()

    wasmJs("web") {
        outputModuleName = "trak"
        browser {
            commonWebpackConfig {
                outputFileName = "trak.js"
                devServer = (devServer ?: KotlinWebpackConfig.DevServer()).apply {
                    static = (static ?: mutableListOf(
                        project.rootDir.path,
                        project.project.path
                    ))
                }
            }
        }
        binaries.executable()
    }

    applyDefaultHierarchyTemplate()

    sourceSets {
        all {
            languageSettings.optIn("kotlin.uuid.ExperimentalUuidApi")
        }

        commonMain {
            dependencies {
                implementation("io.github.oshai:kotlin-logging:7.0.13")
            }
        }

        jvmMain {
            dependencies {
                runtimeOnly("ch.qos.logback:logback-classic:1.5.18")
            }
        }

        commonTest {
            dependencies {
                implementation(kotlin("test"))
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }

        jvmTest.dependencies {
            implementation("org.junit.jupiter:junit-jupiter-engine:5.13.4")
        }
    }
}