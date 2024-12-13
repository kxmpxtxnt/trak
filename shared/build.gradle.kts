@file:OptIn(ExperimentalWasmDsl::class)

import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

plugins {
  alias(agp.plugins.library)
  alias(jetbrains.plugins.kotlin.multiplatform)
}

kotlin {
  androidTarget {
    compilerOptions.jvmTarget = JvmTarget.JVM_11
  }

  iosX64()
  iosArm64()
  iosSimulatorArm64()

  wasmJs {
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
  }

  sourceSets {
    commonMain.dependencies {

    }
  }
}

android {
  namespace = "fyi.pauli.trak.shared"
  compileSdk = agp.versions.android.compileSdk.get().toInt()
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
  }
  defaultConfig {
    minSdk = agp.versions.android.minSdk.get().toInt()
  }
}