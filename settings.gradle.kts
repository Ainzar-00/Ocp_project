pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven { url = uri("https://jitpack.io") }
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
    // ⚠️ Do NOT declare versionCatalogs here.
    // Gradle automatically picks up gradle/libs.versions.toml
}

rootProject.name = "OCP_EvalFormation"
include(":app")