pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Effective Test"
include(":app")
include(":navigation")
include(":feature_main")
include(":feature_catalog")
include(":feature_cart")
include(":feature_stocks")
include(":feature_profile")
include(":main_module")
include(":feature_registration")
include(":core")
