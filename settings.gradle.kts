pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
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

rootProject.name = "prod"
include(":app")
include(":common:navigation")
include(":features:auth:presentation")
include(":common:network")
include(":common:auth:data")
include(":common:auth:domain")
include(":features:main:presentation")
include(":features:main:data")
include(":features:main:domain")
include(":features:sessions:domain")
include(":features:sessions:data")
include(":features:sessions:presentation")
include(":features:auth:data")
include(":features:auth:domain")
