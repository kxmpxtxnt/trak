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
    create("agp").from(files("gradle/agp.versions.toml"))
    create("ktorio").from(files("gradle/ktorio.versions.toml"))
    create("jetbrains").from(files("gradle/jetbrains.versions.toml"))
  }
}