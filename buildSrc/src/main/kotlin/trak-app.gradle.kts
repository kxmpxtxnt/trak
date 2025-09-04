plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
    id("com.android.application")
    id("com.google.devtools.ksp")
    id("org.jetbrains.kotlin.plugin.compose")
}

android {
    namespace = "fyi.pauli.trak"
    compileSdk = 35

    defaultConfig {
        applicationId = "fyi.pauli.trak"
        minSdk = 24
        targetSdk = 35
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
    sourceSets {
        commonMain {
            dependencies {
                implementation(compose.ui)
                implementation(compose.runtime)
                implementation(compose.material3)
                implementation(compose.foundation)
                implementation(compose.components.resources)
                implementation(compose.materialIconsExtended)
                implementation(compose.components.uiToolingPreview)
                implementation(compose.material3AdaptiveNavigationSuite)

                implementation("io.insert-koin:koin-core:4.1.1")
                implementation("io.insert-koin:koin-compose:4.1.1")
                implementation("io.insert-koin:koin-compose-viewmodel-navigation:4.1.1")

                implementation("io.ktor:ktor-serialization-kotlinx-json:3.2.3")
                implementation("io.ktor:ktor-client-auth:3.2.3")
                implementation("io.ktor:ktor-client-resources:3.2.3")
                implementation("io.ktor:ktor-client-content-negotiation:3.2.3")
            }
        }

        androidMain {
            dependencies {
                implementation(compose.preview)

                implementation("androidx.room:room-ktx:2.7.2")
                implementation("androidx.room:room-runtime:2.7.2")

                implementation("androidx.core:core-ktx:1.17.0")
                implementation("androidx.appcompat:appcompat:1.7.1")
                implementation("com.google.android.material:material:1.13.0")
                implementation("androidx.datastore:datastore-preferences:1.1.7")
                implementation("androidx.activity:activity-compose:1.10.1")
                implementation("androidx.test.espresso:espresso-core:3.7.0")
                implementation("androidx.navigation:navigation-compose:2.9.3")
                implementation("androidx.constraintlayout:constraintlayout:2.2.1")
                implementation("org.jetbrains.androidx.lifecycle:lifecycle-viewmodel:2.9.3")
                implementation("org.jetbrains.androidx.lifecycle:lifecycle-runtime-compose:2.9.3")

                implementation("io.insert-koin:koin-android:4.1.1")
                implementation("io.insert-koin:koin-androidx-compose-navigation:4.1.1")
            }
        }

        jvmMain {
            dependencies {
                implementation(compose.desktop.currentOs)
            }
        }

        androidNativeTest {
            dependencies {
                implementation("androidx.test.ext:junit:1.3.0")
            }
        }
    }
}