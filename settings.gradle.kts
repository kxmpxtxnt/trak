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

dependencyResolutionManagement {
  versionCatalogs {
    /**
     * As I plan to make this app as scalable as possible.
     * So I'll split up the dependencies as much as possible.
     */
    create("agp").from(files("gradle/agp.versions.toml"))
    create("koin").from(files("gradle/koin.versions.toml"))
    create("room").from(files("gradle/room.versions.toml"))
    create("google").from(files("gradle/google.versions.toml"))
    create("ktorio").from(files("gradle/ktorio.versions.toml"))
    create("jetbrains").from(files("gradle/jetbrains.versions.toml"))
  }
}