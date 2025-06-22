@file:OptIn(ExperimentalWasmDsl::class)

import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

plugins {
    alias(google.plugins.ksp)
    alias(agp.plugins.appplication)
    alias(jetbrains.plugins.kotlin.compose)
    alias(jetbrains.plugins.kotlin.multiplatform)
    alias(jetbrains.plugins.compose.multiplatform)
}

kotlin {
    androidTarget {
        compilerOptions.jvmTarget = JvmTarget.JVM_11
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { ios ->
        ios.binaries.framework {
            baseName = "trak"
            isStatic = true
        }
    }

    jvm("desktop")

    wasmJs("web") {
        moduleName = "trak"
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

    sourceSets {
        commonMain.dependencies {
            implementation(compose.ui)
            implementation(compose.runtime)
            implementation(compose.material3)
            implementation(compose.foundation)
            implementation(compose.components.resources)
            implementation(compose.materialIconsExtended)
            implementation(compose.components.uiToolingPreview)
            implementation(compose.material3AdaptiveNavigationSuite)

            implementation(koin.core)
            implementation(koin.compose)
            implementation(koin.compose.viewmodel.navigation)

            implementation(agp.androidx.constraintlayout)
        }

        val desktopMain by getting {
            dependencies {
                implementation(compose.desktop.currentOs)
            }
        }

        androidMain.dependencies {
            implementation(compose.preview)

            implementation(koin.android)
            implementation(koin.androidx.compose.navigation)

            implementation(agp.bundles.room)
            implementation(agp.bundles.androidx)
        }
    }
}

dependencies {
    add("kspAndroid", agp.room.compiler)
    debugImplementation(compose.uiTooling)
}


android {
    namespace = "fyi.pauli.trak"
    compileSdk = agp.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "fyi.pauli.trak"
        minSdk = agp.versions.android.minSdk.get().toInt()
        targetSdk = agp.versions.android.targetSdk.get().toInt()
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

compose.desktop {
    application {
        mainClass = "fyi.pauli.trak.TrakKt"

        nativeDistributions {
            targetFormats(TargetFormat.Deb, TargetFormat.Msi, TargetFormat.Dmg)
            packageName = "fyi.pauli.trak"
            packageVersion = rootProject.version.toString()
        }
    }
}

configurations {

}