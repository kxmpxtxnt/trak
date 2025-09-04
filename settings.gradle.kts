rootProject.name = "trak"

include(
    ":app",
    ":server",
    ":shared",
)

pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
